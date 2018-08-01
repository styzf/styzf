package com.styzf.core.web.advice;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.common.util.IPUtils;
import com.styzf.core.common.util.TraceContext;
import com.styzf.core.web.config.StyzfSettings;

@Order(-1)
@ControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    protected final Logger logger = LoggerFactory.getLogger(JsonResponseBodyAdvice.class);
    
    @Autowired
    private StyzfSettings settings;
    
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class clazz, ServerHttpRequest request, ServerHttpResponse response) {
        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse().setHeader("Cache-Control", "no-cache,no-store");
        String contextPath = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getContextPath();
        String requestURI = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        String relativeURI = requestURI.substring(contextPath.length());
        List<String> notResponseAopUrls = settings.getNotResponseAopUrl();
        if ((CollectionUtils.isNotEmpty(notResponseAopUrls)) && (relativeURI != null) && 
                (notResponseAopUrls.contains(relativeURI))) {
            return object;
        }
        
        boolean isRecord = new Boolean(TraceContext.getLocaleWeb()).booleanValue();
        if ((object instanceof SuccessResponseData)) {
            ((SuccessResponseData)object).setProviderSpans(isRecord ? StringUtils.join(TraceContext.getSpans()) : null);
            ((SuccessResponseData)object).setWebIpAddress(IPUtils.getLocalIpAddress());
            ((SuccessResponseData)object).setTraceId(TraceContext.getTraceId() + "");
        }
        if ((object instanceof Response)) {
            return object;
        }
        if (object == null) {
            return SuccessResponseData.newInstance();
        }
        
        SuccessResponseData data = SuccessResponseData.newInstance(object);
        data.setProviderSpans(isRecord ? StringUtils.join(TraceContext.getSpans()) : null);
        data.setWebIpAddress(IPUtils.getLocalIpAddress());
        data.setTraceId(TraceContext.getTraceId() + "");
        return data;
    }
    
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        if (clazz.isAssignableFrom(MappingJackson2HttpMessageConverter.class)) {
            return true;
        }
        return false;
    }
}