package com.styzf.core.common.util;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.MDC;



public class TraceContext {
    private static final ThreadLocal<List<String>> localeSpans = new InheritableThreadLocal();
    
    private static final ThreadLocal<String> localeWeb = new InheritableThreadLocal();
    
    private static final ThreadLocal<Long> requestBegin = new InheritableThreadLocal();
    
    public static void setLoginName(String loginName) {
        MDC.put("loginUserName", loginName);
    }
    
    public static String getLoginName() {
        return MDC.get("loginUserName");
    }
    
    public static void removeLoginName() {
        MDC.remove("loginUserName");
    }
    
    public static String getTraceId() {
        String traceId = MDC.get("traceId");
        return traceId;
    }

    public static void clearTraceId() {
        MDC.remove("traceId");
    }
    
    public static void setTraceId(String traceId) {
      MDC.put("traceId", traceId);
    }
    
    public static void startSpans() {
        localeSpans.set(new ArrayList<>());
    }
    
    public static void addSpan(String ip, String apiName, long times) {
        ((List)localeSpans.get()).add(ip + ":" + apiName + ":×" + times + "ms");
    }
    
    public static void addSpan(String span) {
        ((List)localeSpans.get()).add(span);
    }
    
    public static List<String> getSpans() {
        return (List)localeSpans.get();
    }
    
    public static void clearSpans() {
        localeSpans.remove();
    }
    
    public static void setLocaleWeb(String isWeb) {
        localeWeb.set(isWeb);
    }
    
    public static String getLocaleWeb() {
        return localeWeb.get() == null ? Boolean.FALSE.toString() : (String)localeWeb.get();
    }
    
    public static void clearLocaleWeb() {
        localeWeb.remove();
    }
    
    public static void requestBegin() {
        requestBegin.set(Long.valueOf(System.currentTimeMillis()));
    }
    
    public static Long getRequestBegin() {
        return (Long)requestBegin.get();
    }
    
    public static void clearRequestBegin() {
        requestBegin.remove();
    }
}