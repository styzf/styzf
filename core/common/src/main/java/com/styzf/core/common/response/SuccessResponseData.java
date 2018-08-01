package com.styzf.core.common.response;

import com.styzf.core.common.api.IGetTimeZoneInfo;
import com.styzf.core.common.util.SpringBeanUtil;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.util.ObjectUtils;

/**
 * 成功返回数据实体类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
public class SuccessResponseData extends Response {
    private static String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
    
    /** 返回的数据 */
    private Object data;
    
    /** 请求到的服务器地址  */
    private String webIpAddress;
    
    private String providerSpans;
    
    private String traceId;
    
    private Map<String, Boolean> authorityMap;

    /** 转换器  */
    private Map<String, Format> convertMap;
    
    private TimeZone timeZone;
    
    private DateFormat dateFormat;
    
    
    public static SuccessResponseData newInstance()
    {
      return new SuccessResponseData();
    }
    
    public static SuccessResponseData newInstance(Object data) {
      return new SuccessResponseData(data);
    }
    
    public static SuccessResponseData newInstance(Object data, Map<String, Format> convertMap, Map<String, Boolean> authorityMap)
    {
      return new SuccessResponseData(data, convertMap, authorityMap);
    }
    
    public static SuccessResponseData newInstance(Object data, Map<String, Format> convertMap) {
      return new SuccessResponseData(data, convertMap);
    }
    
    public SuccessResponseData() {
      super(Boolean.valueOf(true));
    }
    
    public SuccessResponseData(Object data) {
      super(Boolean.valueOf(true));
      this.data = data;
    }
    
    public SuccessResponseData(Object data, Map<String, Format> convertMap, Map<String, Boolean> authorityMap) {
      super(Boolean.valueOf(true));
      this.data = data;
      this.convertMap = convertMap;
      this.authorityMap = authorityMap;
    }
    
    public SuccessResponseData(Object data, Map<String, Format> convertMap) {
      super(Boolean.valueOf(true));
      this.data = data;
      this.convertMap = convertMap;
    }
    
    public Object getData() {
      return data;
    }
    
    public void setData(Object data) {
      this.data = data;
    }
    
    
    
    
    
    
    public void addConverter(String key, Format format)
    {
      if (convertMap == null) {
        convertMap = new HashMap();
      }
      
      convertMap.put(key, format);
    }
    
    public Format getConvert(String key) {
      if (convertMap.containsKey(key)) {
        return (Format)convertMap.get(key);
      }
      return null;
    }
    
    
    
    
    
    
    public boolean canConvert(String key)
    {
      if (convertMap == null) {
        return false;
      }
      
      if (convertMap.get(key) == null) {
        return false;
      }
      return true;
    }
    
    
    
    
    
    
    
    public String convert(String key, Object obj)
    {
      Format formater = (Format)convertMap.get(key);
      if (formater != null) {
        return formater.format(obj);
      }
      
      if (ObjectUtils.isEmpty(obj)) {
        return "";
      }
      
      return obj.toString();
    }
    
    public Map<String, Boolean> getAuthorityMap() {
      return authorityMap;
    }
    
    public void setAuthorityMap(Map<String, Boolean> authorityMap) {
      this.authorityMap = authorityMap;
    }
    
    
    
    
    
    
    public SuccessResponseData setTimeZone(TimeZone zone)
    {
      timeZone = zone;
      return this;
    }
    
    
    
    
    
    
    public String formateDate(Object obj) {
      if (ObjectUtils.isEmpty(obj)) {
        return "";
      }
      
      if (dateFormat == null) {
        dateFormat = new SimpleDateFormat(dateFormatPattern);
        
        if (timeZone != null) {
          dateFormat.setTimeZone(timeZone);
        }
        else {
//          IGetTimeZoneInfo getTimeZoneInfo = (IGetTimeZoneInfo)SpringBeanUtil.getBean(IGetTimeZoneInfo.class);
//          if (getTimeZoneInfo != null) {
//            dateFormat.setTimeZone(getTimeZoneInfo.getTimeZone());
//          }
        }
      }
      
      return dateFormat.format(obj);
    }
    
    public String getWebIpAddress() {
      return webIpAddress;
    }
    
    public void setWebIpAddress(String webIpAddress) {
      this.webIpAddress = webIpAddress;
    }
    
    public String getTraceId() {
      return traceId;
    }
    
    public void setTraceId(String traceId) {
      this.traceId = traceId;
    }
    
    public String getProviderSpans() {
      return providerSpans;
    }
    
    public void setProviderSpans(String providerSpans) {
      this.providerSpans = providerSpans;
    }
}
