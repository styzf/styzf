package com.styzf.springboot.mybatisPlus.dataSource;

/**
 * 配置数据节点信息类
 * @author styzf
 * @date 2018年8月1日 
 *
 */
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
