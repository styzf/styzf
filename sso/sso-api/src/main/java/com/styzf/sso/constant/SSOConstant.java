package com.styzf.sso.constant;

public interface SSOConstant {
    public interface Common {
        public static String TOKEN = "styzf_token";
        
        public static String ANONYMOUS_USER = "匿名用户";
    }
    
    public interface Redis {
        public static String SSO_SECURITY_CODE_PREFIX = "sso:securityCode:";
    }
}
