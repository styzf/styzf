package com.styzf.springboot.mybatisPlus.dataSource;

public class DataSourceNodeInfo {
	private String name;
	
	private String writeHost;
	
	private String[] readHost;
	
	private boolean isDefault;
	
	public String getName()
	{
	  return name;
	}
	
	public void setName(String name) {
	  this.name = name;
	}
	
	public String getWriteHost() {
	  return writeHost;
	}
	
	public void setWriteHost(String writeHost) {
	  this.writeHost = writeHost;
	}
	
	public String[] getReadHost() {
	  return readHost;
	}
	
	public void setReadHost(String[] readHost) {
	  this.readHost = readHost;
	}
	
	public boolean isDefault() {
	  return isDefault;
	}
	
	public void setIsDefault(boolean isDefault) {
	  this.isDefault = isDefault;
	}
}
