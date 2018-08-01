package com.styzf.core.common.util.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils extends com.fasterxml.jackson.databind.util.ISO8601Utils {

    public static String getTimeZoneDisplayName(String timeZoneId,Locale locale){
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        String displayName = null;
        if(timeZone == null){
            Calendar cal = Calendar.getInstance();
            timeZone = cal.getTimeZone();
        }
        if(timeZone.getRawOffset() > 0){
            displayName = String.format("(GMT +%s:00)%s" ,timeZone.getRawOffset()/3600000 , timeZone.getDisplayName(locale));
        }else{
            displayName = String.format("(GMT %s:00)%s" ,timeZone.getRawOffset()/3600000 , timeZone.getDisplayName(locale));
        }
        return displayName;
    }

    public static void main(String[] args) throws ParseException {
        String str = getTimeZoneDisplayName("America/St_Lucia",new Locale("zh-CN"));
        System.out.println(DateUtil.dateToString(parse("2017-10-11T14:33:06.390+08:00",new ParsePosition(0)),DateStyle.YYYY_MM_DD_HH_MM));
        Date date = new Date();
        // 获取 “GMT+08:00”对应的时区
        TimeZone china = TimeZone.getTimeZone("Asia/Tomsk");

        // 获取 “中国/重庆”对应的时区
        TimeZone chongqing = TimeZone.getTimeZone("Asia/Tomsk");
        System.out.println(chongqing.getRawOffset()/60/60/1000);
        System.out.println(format(date, Boolean.FALSE, chongqing));
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        Locale locale = Locale.JAPAN;
        System.out.println(timeZone.getID());
        System.out.println(timeZone.getDisplayName(locale));
        System.out.println(getTimeZoneDisplayName("GMT+08:00",Locale.JAPAN));
    }


}
