package com.styzf.core.common.response;

/**
 * 返回实体类的父类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
public class Response {

    protected Boolean success;
    
    protected String msg;
    
    public Response() {}
    
    public Response(Boolean success) {
      this.success = success;
    }
    
    public Response(Boolean success, String msg) {
      this.success = success;
      this.msg = msg;
    }
    
    public Boolean getSuccess() {
      return success;
    }
    
    public void setSuccess(Boolean success) {
      this.success = success;
    }
    
    public String getMsg() {
      return msg;
    }
    
    public void setMsg(String msg) {
      this.msg = msg;
    }
}
