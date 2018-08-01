package com.styzf.springboot.mybatisPlus.dataSource;

import org.apache.commons.lang.StringUtils;

public class DataSourceClusterManager {
	private static String defaultNode;
	private static final ThreadLocal<DataSourceThreadInfo> dataNodeInfo = new ThreadLocal<>();
	
	public static DataSourceThreadInfo get() {
    	DataSourceThreadInfo nodeInfo = (DataSourceThreadInfo)dataNodeInfo.get();
    	if (nodeInfo == null) {
        	String nodeName = getDefaultNode();
        	if (StringUtils.isBlank(nodeName)) {
        	    throw new RuntimeException("系统未配置缺省数据节点");
        	}
        	set(nodeName, false);
        	return (DataSourceThreadInfo)dataNodeInfo.get();
    	}
    	return nodeInfo;
	}
	
	public static void set(String nodeName, boolean readOnly) {
    	if (StringUtils.isBlank(nodeName)) {
        	nodeName = getDefaultNode();
        	
        	if (StringUtils.isBlank(nodeName)) {
        	    throw new RuntimeException("系统未配置缺省数据节点");
        	}
    	}
    	
    	DataSourceThreadInfo nodeInfo = (DataSourceThreadInfo)dataNodeInfo.get();
    	if (nodeInfo != null) {
        	if (!StringUtils.equals(nodeName, nodeInfo.getNodeName()))
        	    throw new RuntimeException("同一线程不允许切换数据源");
        	if (readOnly != nodeInfo.isReadOnly()) {
        	    throw new RuntimeException("同一线程不允许切换数据源读写类型");
        	}
        	return;
    	}
    	
    	dataNodeInfo.set(new DataSourceThreadInfo(nodeName, readOnly));
	}
	
	public static String getDefaultNode() {
	    return defaultNode;
	}
	
	public static void setDefaultNode(String defaultNode) {
	    DataSourceClusterManager.defaultNode = defaultNode;
	}
	
	public static void clean() {
	    dataNodeInfo.remove();
	}
}
