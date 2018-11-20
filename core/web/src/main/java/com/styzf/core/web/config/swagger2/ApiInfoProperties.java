package com.styzf.core.web.config.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="swagger2.apiInfo")
public class ApiInfoProperties {
    private String version;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String pathsRegex;
    private Contact contact;
    
    public String getVersion() {
        return version;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }
    
    public String getLicense() {
        return license;
    }
    
    public String getLicenseUrl() {
        return licenseUrl;
    }
    
    public Contact getContact() {
        return contact;
    }
    
    public String getPathsRegex() {
        return pathsRegex;
    }
    
    public void setPathsRegex(String pathsRegex) {
        this.pathsRegex = pathsRegex;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    
    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}