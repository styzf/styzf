package com.styzf.springboot.mybatisPlus.dataSource;

public class DataSourceThreadInfo {
	private String nodeName;
	
	private boolean isReadOnly;
	
	private String dataSourceKey;
	
	
	public DataSourceThreadInfo(String nodeName, boolean isReadOnly)
	{
	  this.nodeName = nodeName;
	  this.isReadOnly = isReadOnly;
	}
	
	public String getNodeName() {
	  return nodeName;
	}
	
	public void setNodeName(String nodeName) {
	  this.nodeName = nodeName;
	}
	
	public boolean isReadOnly() {
	  return isReadOnly;
	}
	
	public void setReadOnly(boolean isReadOnly) {
	  this.isReadOnly = isReadOnly;
	}
	
	public String getDataSourceKey() {
	  return dataSourceKey;
	}
	
	public void setDataSourceKey(String dataSourceKey) {
	  this.dataSourceKey = dataSourceKey;
	}
}
