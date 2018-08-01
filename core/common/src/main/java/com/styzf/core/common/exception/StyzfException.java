package com.styzf.core.common.exception;

public class StyzfException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;
    
    private String errorKey;
    
    private String errorMsg;
    
    private Object[] params;
    
    private String ipAddress;
    
    private String traceId;

    public StyzfException(Exception e)
    {
      super(e);
    }
    
    public StyzfException(String errorKey) {
      super(errorKey);
      this.errorKey = errorKey;
    }
    
    public StyzfException(String errorKey, Throwable cause) {
      super(errorKey, cause);
      this.errorKey = errorKey;
    }
    
    public StyzfException(String errorKey, String errorMsg) {
      super(errorKey);
      this.errorKey = errorKey;
      this.errorMsg = errorMsg;
    }
    
    public StyzfException(String errorKey, String errorMsg, Throwable cause) {
      super(errorKey, cause);
      this.errorKey = errorKey;
      this.errorMsg = errorMsg;
    }
    
    public StyzfException(String traceId, String ipAddress, String errorMsg, Throwable cause) {
      super(cause);
      this.errorMsg = errorMsg;
      this.traceId = traceId;
      if (null != this.ipAddress) {
        this.ipAddress = (this.ipAddress + ";" + ipAddress);
      } else {
        this.ipAddress = ipAddress;
      }
    }
    
    public String getErrorKey() {
      return errorKey;
    }
    
    public void setErrorKey(String errorKey) {
      this.errorKey = errorKey;
    }
    
    public String getErrorMsg() {
      return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
    }
    
    public Object[] getParams() {
      return params;
    }
    
    public void setParams(Object[] params) {
      this.params = params;
    }
    
    public String getCode() {
      return code;
    }
    
    public void setCode(String code) {
      this.code = code;
    }
    
    public String getIpAddress() {
      return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
    }
    
    public String getTraceId() {
      return traceId;
    }
    
    public void setTraceId(String traceId) {
      this.traceId = traceId;
    }
}
