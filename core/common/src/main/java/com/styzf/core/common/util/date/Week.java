package com.styzf.core.common.util.date;

/**
 * 枚举：周，以周日作为周的第一天，适配中文，英文，日文
 * @author styzf
 * @date 2018年7月25日 
 *
 */
public enum Week {  
	  
    SUNDAY   ("星期日", "Sunday",    "日曜日", "SUN",  1),  
    MONDAY   ("星期一", "Monday",    "月曜日", "MON",  2),  
    TUESDAY  ("星期二", "Tuesday",   "火曜日", "TUES", 3),  
    WEDNESDAY("星期三", "Wednesday", "水曜日", "WED",  4),  
    THURSDAY ("星期四", "Thursday",  "木曜日", "THUR", 5),  
    FRIDAY   ("星期五", "Friday",    "金曜日", "FRI",  6),  
    SATURDAY ("星期六", "Saturday",  "土曜日", "SAT",  7);  
      
    private String name_cn;  
    private String name_en;  
    private String name_jp;  
    private String name_enShort;  
    private int number;  
      
    Week(String name_cn, String name_en, String name_jp, String name_enShort, int number) {  
        this.name_cn = name_cn;  
        this.name_en = name_en;  
        this.name_jp = name_jp;
        this.name_enShort = name_enShort;  
        this.number = number;  
    }  
      
    public String getJapanName() {
        return name_jp;
    }
    
    public String getChineseName() {  
        return name_cn;  
    }  
  
    public String getName() {  
        return name_en;  
    }  
  
    public String getShortName() {  
        return name_enShort;  
    }  
  
    public int getNumber() {  
        return number;  
    }  
}