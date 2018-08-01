package com.styzf.core.common.response;

import com.styzf.core.common.util.IPUtils;

/**
 * 发生异常返回给前端的实体类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
public class ErrorResponseData extends Response {
    protected String errorCode;
    protected String stackMsg;
    protected String traceId;
    protected String providerIpAddress;
    protected String webIpAddress;
    
    public ErrorResponseData(String errorCode, String msg, String stackMsg) {
        super(Boolean.valueOf(false), msg);
        this.errorCode = errorCode;
        this.stackMsg = stackMsg;
    }
    
    public ErrorResponseData(String errorCode, String msg, String stackMsg, String traceId, String providerIpAddress) {
        super(Boolean.valueOf(false), msg);
        this.errorCode = errorCode;
        this.stackMsg = stackMsg;
        this.traceId = traceId;
        this.providerIpAddress = providerIpAddress;
        webIpAddress = IPUtils.getLocalIpAddress();
    }
    
    public ErrorResponseData() {
        super(Boolean.valueOf(false));
    }
    
    public static ErrorResponseData newInstance(String errorCode) {
        return new ErrorResponseData(errorCode, errorCode, null);
    }
    
    public static ErrorResponseData newInstance(String errorCode, String msg) {
        return new ErrorResponseData(errorCode, msg, null);
    }
    
    public static ErrorResponseData newInstance(String errorCode, String msg, String stackMsg) {
        return new ErrorResponseData(errorCode, msg, stackMsg);
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getStackMsg() {
        return stackMsg;
    }
    
    public void setStackMsg(String stackMsg) {
        this.stackMsg = stackMsg;
    }
    
    public String getTraceId() {
        return traceId;
    }
    
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
    
    public String getProviderIpAddress() {
        return providerIpAddress;
    }
    
    public void setProviderIpAddress(String providerIpAddress) {
        this.providerIpAddress = providerIpAddress;
    }
    
    public String getWebIpAddress() {
        return webIpAddress;
    }
    
    public void setWebIpAddress(String webIpAddress) {
        this.webIpAddress = webIpAddress;
    }
}
