package com.styzf.sso.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.stream.IntStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操作工具类
 * @author styzf
 * @date 2018年8月16日 
 *
 */
public final class CookieUtils {
    
    /**
     * 根据Cookie的名称获取指定的Cookie
     * @param request 请求对象
     * @param cookieName cookie的名称
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName){
        /** 获取所有的Cookie */
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
    /**
     * 根据CookieName获取指定的Cookie值
     * @param request 请求对象
     * @param cookieName cookie的名称
     * @param encoded 是否编码
     * @return Cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, 
                    String cookieName, boolean encoded) {
        /** 获取指定的Cookie */
        Cookie cookie = getCookie(request, cookieName);
        String cookieValue = null;
        try {
            if (cookie != null) {
                if (encoded){
                    cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }else{
                    cookieValue = cookie.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookieValue;
    }
 
    /**
     * 根据Cookie的名称删除指定的Cookie
     * @param request 请求对象
     * @param response 响应对象
     * @param cookieName cookie名称
     */
    public static void deleteCookie(HttpServletRequest request, 
                    HttpServletResponse response, String cookieName) {
        setCookie(request, response, cookieName, null, 0, false);
    }
    
    /**
     * 设置Cookie
     * @param request 请求对象
     * @param response 响应对象
     * @param cookieName cookie的名称
     * @param cookieValue cookie的值
     * @param maxAge 存放Cookie的最大存放时间(按秒计算)
     * @param encoded 是否编码
     */
    public static void setCookie(HttpServletRequest request, 
                                 HttpServletResponse response,
                                 String cookieName, String cookieValue, 
                                 int maxAge, boolean encoded) {
        try {
            /** 对Cookie的值进行判断 */
            if (cookieValue == null) {
                cookieValue = "";
            }
            if (encoded) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = getCookie(request, cookieName);
            if (cookie == null){
                cookie = new Cookie(cookieName, cookieValue);
            }
            /** 设置Cookie的值 */
            cookie.setValue(cookieValue);
            /** 设置最大存活时间 */
            cookie.setMaxAge(maxAge);
            if (null != request){
                /** 设置Cookie可以跨二级域名访问 */
                cookie.setDomain(getDomainName(request));
            }
            /** 设置访问路径 */
            cookie.setPath("/");
            /** 添加到用户浏览器 */
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取域名后缀部分，用于设置Cookie跨域
     * @param request 请求对象
     * @return 域名后缀部分
     */
    private static String getDomainName(HttpServletRequest request) {
        /** 定义域名 */
        String domainName = "";
        /** 获取请求URL，并转化成小写字母 */
        String requestURL = request.getRequestURL().toString().toLowerCase();
        /** 判断请求URL */
        if (requestURL != null && !"".equals(requestURL)) {
            /** http://sso.taotao.com/x/x 将请求URL的前缀http协议替换掉 - */
            requestURL = requestURL.replaceFirst("http://", "")
                                   .replaceFirst("https://", "");
            /**  获取URL中第一个/线位置索引号 */
            int end = requestURL.indexOf("/");
            int port = requestURL.indexOf(":");// 端口
            if (port != -1) {
                if (port < end) {
                    end = port;
                }
            }
            /** 截取得到域名部分:  */
            requestURL = requestURL.substring(0, end);
            /** 字符串分隔成数组 */
            String[] domains = requestURL.split("\\.");
            /** 获取数组长度 */
            int len = domains.length;
            /** 判断长度 */
            if (len > 3) {
                // www.xxx.com.cn
                domainName = domains[len - 3] + "." + 
                             domains[len - 2] + "." + 
                             domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = requestURL;
            }
        } 
        return domainName;
    }
}
