package com.styzf.core.web.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("settings")
@ConfigurationProperties(prefix="styzf.config.settings")
public class StyzfSettings {
    public String defaultLocale;
    public List<String> supportLocales = new ArrayList<>();
    
    public Map<String, List<String>> staticMappings = new HashMap<>();
    
    public Map<String, String> validMaps = new HashMap<>();
    
    private List<String> notNeedUserMethods;
    
    /** response不需要处理的url列表 */
    private List<String> notResponseAopUrl = new ArrayList<>();
    
    public List<String> allowedOrigins = new ArrayList<>();
    
    public String hostPrefix;
    
    public String getDefaultLocale() {
        return defaultLocale;
    }
    
    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }
    
    public List<String> getSupportLocales() {
        return supportLocales;
    }
    
    public void setSupportLocales(List<String> supportLocales) {
        this.supportLocales = supportLocales;
    }
    
    public Map<String, List<String>> getStaticMappings() {
        return staticMappings;
    }
    
    public void setStaticMappings(Map<String, List<String>> staticMappings) {
        this.staticMappings = staticMappings;
    }
    
    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }
    
    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
    
    public Map<String, String> getValidMaps() {
        return validMaps;
    }
    
    public void setValidMaps(Map<String, String> validMaps) {
        this.validMaps = validMaps;
    }
    
    public String getHostPrefix() {
        return hostPrefix;
    }
    
    public void setHostPrefix(String hostPrefix) {
        this.hostPrefix = hostPrefix;
    }
    
    public List<String> getNotNeedUserMethods() {
        return notNeedUserMethods;
    }
    
    public void setNotNeedUserMethods(List<String> notNeedUserMethods) {
        this.notNeedUserMethods = notNeedUserMethods;
    }
    
    public List<String> getNotResponseAopUrl() {
        return notResponseAopUrl;
    }
    
    public void setNotResponseAopUrl(List<String> notResponseAopUrl) {
        this.notResponseAopUrl = notResponseAopUrl;
    }
}
