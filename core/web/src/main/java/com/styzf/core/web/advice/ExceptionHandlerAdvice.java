package com.styzf.core.web.advice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.styzf.core.common.config.LocaleMessageSource;
import com.styzf.core.common.exception.StyzfException;
import com.styzf.core.common.response.ErrorResponseData;
import com.styzf.core.common.util.Assert;
import com.styzf.core.common.util.TraceContext;

/**
 * 异常统一处理类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Order(-1)
@ControllerAdvice
public class ExceptionHandlerAdvice {
    protected final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
   
    @Autowired
    private LocaleMessageSource localeMessageSource;
    
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletResponse rps, HttpServletRequest req, RuntimeException ex) {
        logger.error("功能出错", ex);
        String errorCode = ex.getMessage();
        String msg = "";
        String traceId = null;
        String ipAddress = null;
        try {
            msg = localeMessageSource.getMessage(errorCode);
        }
        catch (Exception localException1) {}
        
        if (StringUtils.isEmpty(msg)) {
            try {
                msg = localeMessageSource.getMessage("styzf.system.error");
            } catch (Exception e) {
                msg = "系统内部错误,请联系管理员";
            }
        }
        if ((ex instanceof StyzfException)) {
            traceId = TraceContext.getTraceId() + "";
            ipAddress = null != TraceContext.getSpans() ? StringUtils.join(TraceContext.getSpans().toArray()) : null;
        }
        ErrorResponseData data = new ErrorResponseData();
        data.setErrorCode(errorCode);
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        data.setTraceId(traceId);
        data.setProviderIpAddress(ipAddress);
        return data;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletRequest req, ValidationException ex) {
        Throwable cause = ex.getCause();
        String errorCode = null;
        String msg = null;
        String traceId = null;
        String ipAddress = null;
        if (cause instanceof StyzfException) {
            StyzfException exception = (StyzfException) cause;
            errorCode = exception.getErrorKey();
            msg = exception.getErrorMsg();
            traceId = null;
            ipAddress = null;
        }
        if (StringUtils.isEmpty(msg)) {
            try {
                msg = localeMessageSource.getMessage("styzf.system.error");
            } catch (Exception e) {
                msg = "系统内部错误,请联系管理员";
            }
        }
        ErrorResponseData data = new ErrorResponseData();
        data.setErrorCode(errorCode);
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        data.setTraceId(traceId);
        data.setProviderIpAddress(ipAddress);
        return data;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({StyzfException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletRequest req, StyzfException ex) {
        String errorCode = ex.getErrorKey();
        String msg = ex.getErrorMsg();
        String traceId = null;
        String ipAddress = null;
        if (StringUtils.isEmpty(msg)) {
            try {
                msg = localeMessageSource.getMessage("styzf.system.error");
            } catch (Exception e) {
                msg = "系统内部错误,请联系管理员";
            }
        }
        if ((ex instanceof StyzfException)) {
            traceId = ex.getTraceId();
            ipAddress = ex.getIpAddress();
        }
        ErrorResponseData data = new ErrorResponseData();
        data.setErrorCode(errorCode);
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        data.setTraceId(traceId);
        data.setProviderIpAddress(ipAddress);
        return data;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletResponse rps, HttpServletRequest req, BindException ex) {
        logger.error("参数绑定出错", ex);
        ErrorResponseData data = new ErrorResponseData();
        String msg = null;
        try {
            msg = localeMessageSource.getMessage("styzf.system.error");
        } catch (Exception e) {
            msg = "参数绑定出错";
        }
        data.setErrorCode(ex.getMessage());
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        return data;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ServletException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletResponse rps, HttpServletRequest req, ServletException ex) {
        logger.error("ServletException出错", ex);
        ErrorResponseData data = new ErrorResponseData();
        String msg = null;
        try {
            msg = localeMessageSource.getMessage("styzf.system.error");
        } catch (Exception e) {
            msg = "ServletException出错";
        }
        data.setErrorCode(ex.getMessage());
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        return data;
    }
}

