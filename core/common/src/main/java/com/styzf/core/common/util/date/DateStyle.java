package com.styzf.core.common.util.date;

/**
 * 
 * @author styzf
 * @date 2018年7月25日 
 *
 */
public enum DateStyle {  
    
    YYYY_M("yyyy-M", false),  
    YYYY_M_D("yyyy-M-d", false),  
    YYYY_M_D_H_M("yyyy-M-d H:m", false),  
    YYYY_M_D_H_M_S("yyyy-M-d H:m:s", false),
    
    YY_M("yy-M", false),  
    YY_M_D("yy-M-d", false),  
    YY_M_D_H_M("yy-M-d H:m", false),  
    YY_M_D_H_M_S("yy-M-d H:m:s", false),
    
    YYYY_MM("yyyy-MM", false),  
    YYYY_MM_DD("yyyy-MM-dd", false),  
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", false),  
    
    YYYY_M_UNDER_LINE("yyyy_MM", false),  
    YYYY_M_D_UNDER_LINE("yyyy_MM_dd", false),  
    YYYY_M_D_H_M_UNDER_LINE("yyyy_MM_dd HH:mm", false),  
    YYYY_M_D_H_M_S_UNDER_LINE("yyyy_MM_dd HH:mm:ss", false),  
      
    YYYY_MM_EN("yyyy/MM", false),  
    YYYY_MM_DD_EN("yyyy/MM/dd", false),  
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", false),
    _YYYY_MM_DD("_yyyy_M_d", false),

    YYYYMM("yyyyMM", false),  
    YYYYMMDD("yyyyMMdd", false),  
    YYYYMMDDHHMM("yyyyMMdd HH:mm", false),  
    YYYYMMDDHHMMSS("yyyyMMdd HH:mm:ss", false),  
      
    YYYY_MM_CN("yyyy年MM月", false),  
    YYYY_MM_DD_CN("yyyy年MM月dd日", false),  
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", false),  
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss", false),  
      
    HH_MM("HH:mm", true),  
    HH_MM_SS("HH:mm:ss", true),  
      
    MM_DD("MM-dd", true),  
    MM_DD_HH_MM("MM-dd HH:mm", true),  
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", true),  
      
    MM_DD_EN("MM/dd", true),  
    MM_DD_HH_MM_EN("MM/dd HH:mm", true),  
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", true),  
      
    MM_DD_CN("MM月dd日", true),  
    MM_DD_HH_MM_CN("MM月dd日 HH:mm", true),  
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", true);  
      
    private String value;  
      
    private boolean isShowOnly;  
      
    DateStyle(String value, boolean isShowOnly) {  
        this.value = value;  
        this.isShowOnly = isShowOnly;  
    }  
      
    public String getValue() {  
        return value;  
    }  
      
    public boolean isShowOnly() {  
        return isShowOnly;  
    }  
}