package com.styzf.springboot.mybatisPlus.dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态选择数据源
 * @author styzf
 * @date 2018年8月1日 
 *
 */
public class DynamicRountingDataSource extends AbstractRoutingDataSource {
    private Map<String, Integer> dataSourceCount;
    private static final Logger logger = LoggerFactory.getLogger(DynamicRountingDataSource.class);
    
    protected Object determineCurrentLookupKey() {
        DataSourceThreadInfo dataSourceThreadInfo = DataSourceClusterManager.get();
        String dataSourceKey = dataSourceThreadInfo.getDataSourceKey();
        
        if (StringUtils.isBlank(dataSourceKey)) {
            if (dataSourceThreadInfo.isReadOnly()) {
                Integer count = (Integer)dataSourceCount.get(String.format("%s.readHost", new Object[] { dataSourceThreadInfo.getNodeName() }));
                if ((count == null) || (count.intValue() == 0)) {
                  dataSourceKey = String.format("%s.writeHost", new Object[] { dataSourceThreadInfo.getNodeName() });
                } else if (count.intValue() == 1) {
                  dataSourceKey = String.format("%s.readHost0", new Object[] { dataSourceThreadInfo.getNodeName() });
                }
                else {
                  long time = System.currentTimeMillis();
                  dataSourceKey = String.format("%s.readHost%d", new Object[] { dataSourceThreadInfo.getNodeName(), Long.valueOf(time % count.intValue()) });
                }
            }
            else {
                dataSourceKey = String.format("%s.writeHost", new Object[] { dataSourceThreadInfo.getNodeName() });
            }
            
            dataSourceThreadInfo.setDataSourceKey(dataSourceKey);
        }
        return dataSourceKey;
    }
    
    public void setTargetDataSources(Map<Object, Object> targetDataSources, Map<String, Integer> dataSourceCount) {
        super.setTargetDataSources(targetDataSources);
        this.dataSourceCount = dataSourceCount;
    }
    
    public Connection getConnection() throws SQLException {
        Connection connection = determineTargetDataSource().getConnection();
        DataSourceThreadInfo dataSourceThreadInfo = DataSourceClusterManager.get();
        
        if (dataSourceThreadInfo.isReadOnly()) {
            connection.setReadOnly(true);
            if (logger.isDebugEnabled()) {
                logger.debug("当前Connection为只读节点,nodeName:{},dataSourceKey:{}", dataSourceThreadInfo.getNodeName(), dataSourceThreadInfo.getDataSourceKey());
            }
        }
        return connection;
    }
}
