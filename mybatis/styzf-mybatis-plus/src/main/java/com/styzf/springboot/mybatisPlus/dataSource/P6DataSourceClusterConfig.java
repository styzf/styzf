package com.styzf.springboot.mybatisPlus.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.p6spy.engine.spy.P6DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 数据库读写分离集群配置类
 * @author styzf
 * @date 2018年7月20日 
 *
 */
@Component
@Configuration
@ConfigurationProperties(prefix="spring.datasource-cluster")
public class P6DataSourceClusterConfig {
    private static final Log logger = LogFactory.getLog(P6DataSourceClusterConfig.class);
    
    @Autowired
    private Environment environment;
    
    private String platform;
    List<DataSourceNodeInfo> nodes = new ArrayList<>();
    
    @Bean("dataSoure")
    public DataSource dataSource() {
        if (logger.isInfoEnabled()) {
            logger.info(StringUtils.center(" new dataSource! ", 80, "*"));
        }
        
        DynamicRountingDataSource ds = new DynamicRountingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, Integer> dataSourceCount = new HashMap<>();
        
        if (nodes.size() > 0) {
            for (DataSourceNodeInfo node : nodes) {
                if (node.isDefault()) {
                    if (StringUtils.isNotBlank(DataSourceClusterManager.getDefaultNode())) {
                      throw new RuntimeException("系统配置了多个缺省数据节点");
                    }
                    DataSourceClusterManager.setDefaultNode(node.getName());
                }
                
                
                targetDataSources.put(String.format("%s.writeHost", new Object[] { node.getName() }), 
                        constructDataSource(node.getWriteHost()));
                dataSourceCount.put(String.format("%s.writeHost", new Object[] { node.getName() }), Integer.valueOf(1));
                
                
                if ((node.getReadHost() != null) && (node.getReadHost().length > 0)) {
                    for (int i = 0; i < node.getReadHost().length; i++) {
                      targetDataSources.put(String.format("%s.readHost%d", new Object[] { node.getName(), Integer.valueOf(i) }), 
                              constructDataSource(node.getReadHost()[i]));
                    }
                    dataSourceCount.put(String.format("%s.readHost", new Object[] { node.getName() }), Integer.valueOf(node.getReadHost().length));
                }
            }
            
            if (StringUtils.isBlank(DataSourceClusterManager.getDefaultNode())) {
                throw new RuntimeException("系统未配置缺省数据节点");
            }
        }
        else {
            DataSourceClusterManager.setDefaultNode("datasource");
            targetDataSources.put("datasource.writeHost", constructDataSource("datasource"));
            dataSourceCount.put("datasource.writeHost", Integer.valueOf(1));
        }
        
        ds.setTargetDataSources(targetDataSources, dataSourceCount);
        return ds;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public List<DataSourceNodeInfo> getNodes() {
        return nodes;
    }
    
    public void setNodes(List<DataSourceNodeInfo> nodes) {
        this.nodes = nodes;
    }

    /**
     * 创建数据库连接池DruidDataSource
     * @author styzf
     * @date 2018年7月20日 
     * @param dsName 配置名yml中配置nodes的节点名
     * @return
     */
    private DataSource constructDataSource(String dsName) {
        if (StringUtils.isBlank(platform)) {
            platform = environment.getProperty(String.format("spring.%s.platform", new Object[] { dsName }));
        }
        
        String url = environment.getProperty(String.format("spring.%s.url", new Object[] { dsName }));
        String username = environment.getProperty(String.format("spring.%s.username", new Object[] { dsName }));
        String password = environment.getProperty(String.format("spring.%s.password", new Object[] { dsName }));
        String driverClassName = environment.getProperty(String.format("spring.%s.driverClassName", new Object[] { dsName }));
        int initialSize = ((Integer)environment.getProperty(String.format("spring.%s.initialSize", new Object[] { dsName }), Integer.class)).intValue();
        int minIdle = ((Integer)environment.getProperty(String.format("spring.%s.minIdle", new Object[] { dsName }), Integer.class)).intValue();
        int maxActive = ((Integer)environment.getProperty(String.format("spring.%s.maxActive", new Object[] { dsName }), Integer.class)).intValue();
        long maxWait = ((Long)environment.getProperty(String.format("spring.%s.maxWait", new Object[] { dsName }), Long.class)).longValue();
        String validationQuery = environment.getProperty(String.format("spring.%s.validationQuery", new Object[] { dsName }));
        boolean testOnBorrow = ((Boolean)environment.getProperty(String.format("spring.%s.testOnBorrow", new Object[] { dsName }), Boolean.class)).booleanValue();
        boolean testOnReturn = ((Boolean)environment.getProperty(String.format("spring.%s.testOnReturn", new Object[] { dsName }), Boolean.class)).booleanValue();
        boolean testWhileIdle = ((Boolean)environment.getProperty(String.format("spring.%s.testWhileIdle", new Object[] { dsName }), Boolean.class)).booleanValue();
        
        
        long minEvictableIdleTimeMillis = ((Long)environment.getProperty(String.format("spring.%s.minEvictableIdleTimeMillis", new Object[] { dsName }), Long.class)).longValue();
        long timeBetweenEvictionRunsMillis = ((Long)environment.getProperty(String.format("spring.%s.timeBetweenEvictionRunsMillis", new Object[] { dsName }), Long.class)).longValue();
        boolean removeAbandoned = ((Boolean)environment.getProperty(String.format("spring.%s.removeAbandoned", new Object[] { dsName }), Boolean.class)).booleanValue();
        int removeAbandonedTimeout = ((Integer)environment.getProperty(String.format("spring.%s.removeAbandonedTimeout", new Object[] { dsName }), Integer.class)).intValue();
        boolean poolPreparedStatements = ((Boolean)environment.getProperty(String.format("spring.%s.poolPreparedStatements", new Object[] { dsName }), Boolean.class)).booleanValue();
        int maxPoolPreparedStatementPerConnectionSize = ((Integer)environment.getProperty(
                String.format("spring.%s.maxPoolPreparedStatementPerConnectionSize", new Object[] { dsName }), Integer.class)).intValue();
        boolean logAbandoned = ((Boolean)environment.getProperty(String.format("spring.%s.logAbandoned", new Object[] { dsName }), Boolean.class)).booleanValue();
        String filters = environment.getProperty(String.format("spring.%s.filters", new Object[] { dsName }));
        
        boolean useGlobalDataSourceStat = ((Boolean)environment.getProperty(String.format("spring.%s.useGlobalDataSourceStat", new Object[] { dsName }), Boolean.class)).booleanValue();
        Boolean defaultReadOnly = (Boolean)environment.getProperty(String.format("spring.%s.defaultReadOnly", new Object[] { dsName }), Boolean.class);
        
        
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setRemoveAbandoned(removeAbandoned);
        datasource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setLogAbandoned(logAbandoned);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        if (defaultReadOnly != null) {
            datasource.setDefaultReadOnly(defaultReadOnly);
        }
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("注入druid filters异常!", e);
        }
        
        P6DataSource p6DataSource = new P6DataSource(datasource);
        return p6DataSource;
    }
}
