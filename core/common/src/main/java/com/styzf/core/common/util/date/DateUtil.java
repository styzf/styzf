package com.styzf.core.common.util.date;

import com.styzf.core.common.constant.DateConstant;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author styzf
 * @date 2018年7月25日 
 *
 */
public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
  
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
      
    private static final Object object = new Object();

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    /** 
     * 获取SimpleDateFormat 
     * @param pattern 日期格式 
     * @return SimpleDateFormat对象 
     * @throws RuntimeException 异常：非法日期格式 
     */  
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    private static String test(){
        return "a";
    }

    /** 
     * 获取日期中的某数值。如获取月份 
     * @param date 日期 
     * @param dateType 日期格式 
     * @return 数值 
     */  
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }
  
    /** 
     * 增加日期中某类型的某数值。如增加日期 
     * @param date 日期字符串 
     * @param dateType 类型 
     * @param amount 数值 
     * @return 计算后日期字符串 
     */  
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = stringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = dateToString(myDate, dateStyle);
        }
        return dateString;
    }
  
    /** 
     * 增加日期中某类型的某数值。如增加日期 
     * @param date 日期 
     * @param dateType 类型 
     * @param amount 数值 
     * @return 计算后日期 
     */  
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }
  
    /** 
     * 获取精确的日期 
     * @param timestamps 时间long集合 
     * @return 日期 
     */  
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();
  
        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j) };
                        map.put(absoluteValue, timestampTmp);
                    }
                }
  
                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0  
                // 因此不能将minAbsoluteValue取默认值0  
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }
  
                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
  
                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne :dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }
  
        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }
  
    /** 
     * 判断字符串是否为日期字符串 
     * @param date 日期字符串 
     * @return true or false 
     */  
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }
  
    /** 
     * 获取日期字符串的日期风格。失敗返回null。 
     * @param date 日期字符串 
     * @return 日期风格 
     */  
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                }catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @return 日期 
     */  
    public static Date stringToDate(String date) {
        DateStyle dateStyle = getDateStyle(date);
        return stringToDate(date, dateStyle);
    }
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param pattern 日期格式 
     * @return 日期 
     */  
    public static Date stringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            }catch (Exception e) {
            	logger.error("日期(" + date + ")的格式不正确，必须为：" + pattern, e);
            }
        }
        return myDate;
    }
  
    /** 
     * 将日期字符串转化为日期。失败返回null。 
     * @param date 日期字符串 
     * @param dateStyle 日期风格 
     * @return 日期 
     */  
    public static Date stringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = stringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }
  
    /** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param pattern 日期格式 
     * @return 日期字符串 
     */  
    public static String dateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            }catch (Exception e) {
            	logger.error("日期(" + date + ")转换后的格式(" + pattern + ")不正确", e);
            }
        }
        return dateString;
    }
  
    /** 
     * 将日期转化为日期字符串。失败返回null。 
     * @param date 日期 
     * @param dateStyle 日期风格 
     * @return 日期字符串 
     */  
    public static String dateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = dateToString(date, dateStyle.getValue());
        }
        return dateString;
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param newPattern 新日期格式 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, String newPattern) {
        DateStyle oldDateStyle = getDateStyle(date);
        return stringToString(date, oldDateStyle, newPattern);
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param newDateStyle 新日期风格 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = getDateStyle(date);
        return stringToString(date, oldDateStyle, newDateStyle);
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddPattern 旧日期格式 
     * @param newPattern 新日期格式 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, String olddPattern, String newPattern) {
        return dateToString(stringToDate(date, olddPattern), newPattern);
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddDteStyle 旧日期风格 
     * @param newParttern 新日期格式 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, DateStyle olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = stringToString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddPattern 旧日期格式 
     * @param newDateStyle 新日期风格 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, String olddPattern, DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = stringToString(date, olddPattern, newDateStyle.getValue());
        }
        return dateString;
    }
  
    /** 
     * 将日期字符串转化为另一日期字符串。失败返回null。 
     * @param date 旧日期字符串 
     * @param olddDteStyle 旧日期风格 
     * @param newDateStyle 新日期风格 
     * @return 新日期字符串 
     */  
    public static String stringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = stringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }
    
    public static List<Date> dateSplit(Date startDate, Date endDate) {
    	/*if (!startDate.before(endDate)) {
    		logger.error("开始时间应该在结束时间之后");
    		return null;
    	}*/
	    Long spi = endDate.getTime() - startDate.getTime();
	    Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

	    List<Date> dateList = new ArrayList<Date>();
	    dateList.add(endDate);
	    for (int i = 1; i <= step; i++) {
	        dateList.add(new Date(dateList.get(i - 1).getTime()
	                - (24 * 60 * 60 * 1000)));// 比上一天减一
	    }
	    return dateList;
    }
    
    /**
     * 获得当前日期与本周第一天相差的天数
     * @param date
     * 				日期
     * @param startWeekDay
     * 				一周开始的日期.默认为:Calendar.SUNDAY
     * @return
     */
    private static int getDatePlus(Date date, int startWeekDay) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == startWeekDay) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }
    
    /**
     * 获得当前周第一天的日期
     * @param date
     * 				日期
     * @param startWeekDay
     * 				一周开始的日期.默认为:Calendar.SUNDAY
     * @return
     */
    public static Date getCurrentWeekFirstDay(Date date, int startWeekDay) {
        int dayPlus = getDatePlus(date, startWeekDay);
        Date firstDate = DateUtil.addDay(date, dayPlus);
        return firstDate;
    }
    
    /**
     * 获取当周最后日期的日期
     * @param date
     * 				日期
     * @param startWeekDay
     * 				一周开始的日期.默认为:Calendar.SUNDAY
     * @return
     */
    public static Date getCurrentWeekLastDay(Date date, int startWeekDay) {
        int dayPlus = getDatePlus(date, startWeekDay);
        Date lastDate = DateUtil.addDay(date, dayPlus + 6);
        return lastDate;
    }
    
    /**
     * 根据周次获取该周最后一天
     * @param year
     * @param weekOfYear
     * @param startWeekDay
     * @return
     */
    public static Date getLastWeekDay(int year, int weekOfYear, int startWeekDay) {
    	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, startWeekDay);
        return cal.getTime();
    }
    
    /**
     * 获取日期所属当年的周次
     * @param date
     * @param startWeekDay
     * @return
     */
    public static int getWeekOfYearIndex(Date date, int startWeekDay) {
    	 Calendar calendar = Calendar.getInstance();  
    	 calendar.setFirstDayOfWeek(startWeekDay);  
    	 calendar.setTime(date);
    	 return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    /** 
     * 增加日期的年份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加年份后的日期字符串 
     */  
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }
  
    /** 
     * 增加日期的年份。失败返回null。 
     * @param date 日期 
     * @param yearAmount 增加数量。可为负数 
     * @return 增加年份后的日期 
     */  
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }
  
    /** 
     * 增加日期的月份。失败返回null。 
     * @param date 日期 
     * @param monthAmount 增加数量。可为负数 
     * @return 增加月份后的日期字符串 
     */  
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }
  
    /** 
     * 增加日期的月份。失败返回null。 
     * @param date 日期 
     * @param monthAmount 增加数量。可为负数 
     * @return 增加月份后的日期 
     */  
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }
  
    /** 
     * 增加日期的天数。失败返回null。 
     * @param date 日期字符串 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加天数后的日期字符串 
     */  
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }
  
    /** 
     * 增加日期的天数。失败返回null。 
     * @param date 日期 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加天数后的日期 
     */  
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }
  
    /** 
     * 增加日期的小时。失败返回null。 
     * @param date 日期字符串 
     * @param hourAmount 增加数量。可为负数 
     * @return 增加小时后的日期字符串 
     */  
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }
  
    /** 
     * 增加日期的小时。失败返回null。 
     * @param date 日期 
     * @param hourAmount 增加数量。可为负数 
     * @return 增加小时后的日期 
     */  
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }
  
    /** 
     * 增加日期的分钟。失败返回null。 
     * @param date 日期字符串 
     * @param minuteAmount 增加数量。可为负数 
     * @return 增加分钟后的日期字符串 
     */  
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }
  
    /** 
     * 增加日期的分钟。失败返回null。 
     * @param date 日期 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加分钟后的日期 
     */  
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }
  
    /** 
     * 增加日期的秒钟。失败返回null。 
     * @param date 日期字符串 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加秒钟后的日期字符串 
     */  
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }
  
    /** 
     * 增加日期的秒钟。失败返回null。 
     * @param date 日期 
     * @param dayAmount 增加数量。可为负数 
     * @return 增加秒钟后的日期 
     */  
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }
  
    /** 
     * 获取日期的年份。失败返回0。 
     * @param date 日期字符串 
     * @return 年份 
     */  
    public static int getYear(String date) {
        return getYear(stringToDate(date));
    }
  
    /** 
     * 获取日期的年份。失败返回0。 
     * @param date 日期 
     * @return 年份 
     */  
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }
  
    /** 
     * 获取日期的月份。失败返回0。 
     * @param date 日期字符串 
     * @return 月份 
     */  
    public static int getMonth(String date) {
        return getMonth(stringToDate(date));
    }
  
    /** 
     * 获取日期的月份。失败返回0。 
     * @param date 日期 
     * @return 月份 
     */  
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }
  
    /** 
     * 获取日期的天数。失败返回0。 
     * @param date 日期字符串 
     * @return 天 
     */  
    public static int getDay(String date) {
        return getDay(stringToDate(date));
    }
  
    /** 
     * 获取日期的天数。失败返回0。 
     * @param date 日期 
     * @return 天 
     */  
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }
  
    /** 
     * 获取日期的小时。失败返回0。 
     * @param date 日期字符串 
     * @return 小时 
     */  
    public static int getHour(String date) {
        return getHour(stringToDate(date));
    }
  
    /** 
     * 获取日期的小时。失败返回0。 
     * @param date 日期 
     * @return 小时 
     */  
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }
  
    /** 
     * 获取日期的分钟。失败返回0。 
     * @param date 日期字符串 
     * @return 分钟 
     */  
    public static int getMinute(String date) {
        return getMinute(stringToDate(date));
    }
  
    /** 
     * 获取日期的分钟。失败返回0。 
     * @param date 日期 
     * @return 分钟 
     */  
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }
  
    /** 
     * 获取日期的秒钟。失败返回0。 
     * @param date 日期字符串 
     * @return 秒钟 
     */  
    public static int getSecond(String date) {
        return getSecond(stringToDate(date));
    }
  
    /** 
     * 获取日期的秒钟。失败返回0。 
     * @param date 日期 
     * @return 秒钟 
     */  
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }
    /**
     * 获取日期的毫秒值
     * @param date
     * @return
     */
    public static int getMilliSecond(Date date) {
		return getInteger(date, Calendar.MILLISECOND);
	}
  
    /** 
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。 
     * @param date 日期字符串 
     * @return 日期 
     */  
    public static String getDate(String date) {
        return stringToString(date, DateStyle.YYYY_MM_DD);
    }
  
    /** 
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。 
     * @param date 日期 
     * @return 日期 
     */  
    public static String getDate(Date date) {
        return dateToString(date, DateStyle.YYYY_MM_DD);
    }
  
    /** 
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
     * @param date 日期字符串 
     * @return 时间 
     */  
    public static String getTime(String date) {
        return stringToString(date, DateStyle.HH_MM_SS);
    }
  
    /** 
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。 
     * @param date 日期 
     * @return 时间 
     */  
    public static String getTime(Date date) {
        return dateToString(date, DateStyle.HH_MM_SS);
    }
  
    /** 
     * 获取日期的星期。失败返回null。 
     * @param date 日期字符串 
     * @return 星期 
     */  
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = stringToDate(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }
  
    /** 
     * 获取日期的星期。失败返回null。 
     * @param date 日期 
     * @return 星期 
     */  
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
        case 0:
            week = Week.SUNDAY;
            break;
        case 1:
            week = Week.MONDAY;
            break;
        case 2:
            week = Week.TUESDAY;
            break;
        case 3:
            week = Week.WEDNESDAY;
            break;
        case 4:
            week = Week.THURSDAY;
            break;
        case 5:
            week = Week.FRIDAY;
            break;
        case 6:
            week = Week.SATURDAY;
            break;
        }
        return week;
    }
  
    /** 
     * 获取两个日期相差的天数 
     * @param date 日期字符串 
     * @param otherDate 另一个日期字符串 
     * @return 相差天数。如果失败则返回-1 
     */  
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(stringToDate(date), stringToDate(otherDate));
    }
  
    /** 
     * @param date 日期 
     * @param otherDate 另一个日期 
     * @return 相差天数。如果失败则返回-1 
     */  
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtil.stringToDate(DateUtil.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtil.stringToDate(DateUtil.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }
    
    /** 
     * Description:判断一个日期是否为工作日(非周六周日) 
     *  
     * @param date 
     * @return 
     * @since：2007-12-13 下午03:01:35 
     */  
    public static boolean isWorkDay(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
            return false;
        return true;
    }
    
    /** 
     * Description:指定日期所在月的第一天
     *  
     * @param date 
     * @return 
     * @since：2007-12-13 下午03:28:21 
     */  
    public static Date getMonthStartDay(Date date) {
    	// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    
    /** 
     * Description:指定日期所在月的最后一天 
     *  
     * @param date 
     * @return 
     * @since：2007-12-13 下午03:28:21 
     */  
    public static Date getMonthEndDay(Date date) {
    	// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    /** 
     * 指定日期所在旬的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getTenDaysStart(Date date) {
    	// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH) / 10 * 10 + 1;
        if (calendar.get(Calendar.DAY_OF_MONTH) % 10 == 0 || day == 31) {
            day = day - 10;
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    
    /** 
     * 指定日期所在旬的最后一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getTenDaysEnd(Date date) {
    	// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_MONTH) / 10 == 2  
                && calendar.get(Calendar.DAY_OF_MONTH) != 20)  
            return getMonthEndDay(date);
        else  
            return addDay(getTenDaysStart(addDay(date, 10)), -1);  
    }
    
    /** 
     * 指定日期所在季度的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getQuarterStart(Date date) {
    	// 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        int month = (calendar.get(Calendar.MONTH) / 3) * 3;
        calendar.set(Calendar.MONTH, month);
        return getMonthStartDay(calendar.getTime());
    }

    /**
     * 把毫秒转化成日期
     * @param millSec(毫秒数)
     * @return
     */
    public static Date transferLongToDate(Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        Date date= new Date(millSec);
        return date;
    }

    /**
     *
     * @param orgDateStr 原始时间字符串类型
     * @param unit 单位 1:分钟、2:小时、3:天
     * @param value 相差多久
     * @param type 类型，1为再时间前，0为在时间后
     * @return
     */
    public static Date dealWithDate(String orgDateStr,String unit,int value,int type){
        Date orgDate = stringToDate(orgDateStr);
        long calculate = 0;
        if(orgDate == null){
            return null;
        }
        switch (unit){
            case "1":
                calculate = (long)value*60*1000;
                break;
            case "2":
                calculate =(long)value*60*60*1000;
                break;
            case "3":
                calculate = (long)value*24*60*60*1000;
                break;
        }
        if(type == 0){
            return transferLongToDate(orgDate.getTime()-calculate);
        }if(type == 1){
            return transferLongToDate(orgDate.getTime()+calculate);
        }
        return null;
    }

    /**
     *
     * @param orgDate 原始时间时间类型
     * @param unit 单位 1:分钟、2:小时、3:天
     * @param value 相差多久
     * @return
     */
    public static Date dealWithDate(Date orgDate,String unit,double value){
        long calculate = 0;
        switch (unit){
            case "MINUTE":
                calculate = new Double(value*60*1000).longValue();
                break;
            case "HOUR":
                calculate = new Double(value*60*60*1000).longValue();
                break;
            case "DAY":
                calculate = new Double(value*24*60*60*1000).longValue();
                break;
        }
        if(orgDate == null){
            return null;
        }

        return transferLongToDate(orgDate.getTime()+calculate);
    }

    /**
     * @param srcDate 时间
     * @param srcTimeZoneId 原来时区
     * @param dstTimeZoneId 转换后的时区
     * @return
     */
    public static Date timezoneDateChange(Date srcDate, String srcTimeZoneId, String dstTimeZoneId) {
        if(srcDate == null) {
            return null;
        } else if(dstTimeZoneId != null && !"".equals(dstTimeZoneId.trim())) {
            if(srcTimeZoneId == null || "".equals(srcTimeZoneId.trim())) {
                srcTimeZoneId = TimeZone.getDefault().getID();
            }

            int diffTime = TimeZone.getTimeZone(srcTimeZoneId).getRawOffset() - TimeZone.getTimeZone(dstTimeZoneId).getRawOffset();
            long nowTime = srcDate.getTime();
            long newNowTime = nowTime - (long)diffTime;
            return new Date(newNowTime);
        } else {
            return srcDate;
        }
    }



    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }
    /**
     * 获取当月最后一天的日期，返回Date，该Date除日期与星期外，其余参数与传入Date一致
     * @param date
     * @return
     */
    public static Date getLastDayOfMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear(date));
        cal.set(Calendar.MONTH, getMonth(date) - 1);
        cal.set(Calendar.HOUR_OF_DAY, getHour(date));
        cal.set(Calendar.MINUTE, getMinute(date));
        cal.set(Calendar.SECOND, getSecond(date));
        cal.set(Calendar.MILLISECOND, getMilliSecond(date));
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    public static String getFirstDayOfMonth(int year, int month) {
        return new SimpleDateFormat( "yyyy-MM-dd ").format(getFirstDayOfMonthDate(year, month));
    }
    
    /**
     * 根据传入的值，获取该月第一天的时间
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonthDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     * 获取当月最后一天的日期，返回Date，该Date除日期与星期外，其余参数与传入Date一致
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear(date));
        cal.set(Calendar.MONTH, getMonth(date) - 1);
        cal.set(Calendar.HOUR_OF_DAY, getHour(date));
        cal.set(Calendar.MINUTE, getMinute(date));
        cal.set(Calendar.SECOND, getSecond(date));
        cal.set(Calendar.MILLISECOND, getMilliSecond(date));
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return cal.getTime();
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    public static Date getYearMS(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = dateFormat.format(date);
        if(dateFormat.format(date) != null){
            return stringToDate(str,DateStyle.YYYY_MM_DD);
        }
        return null;
    }

    public static Date getHourTime(Date date){
        DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
        if(date == null){
            return null;
        }
        return stringToDate(df3.format(date),DateStyle.HH_MM);
    }

    public static Long getHourTimeToLong(Date date){
        Long l = new Long("0");
        if(date == null){
            return l;
        }if(getHourTime(date) ==  null){
            return l;
        }
        return getHourTime(date).getTime();
    }

    public static Long getYearMSTimeToLong(Date date){
        Long l = new Long("0");
        if(date == null){
            return l;
        }if(getYearMS(date) ==  null){
            return l;
        }
        return getYearMS(date).getTime();
    }

    /**
     *
     * @param date 日期
     * @param calendarTimeZone 日程时区
     * @param userTimeZone 用户时区
     * @param utcOffset 浏览器时差
     * @return
     */
    public static Date dealTimezome(Date date ,String calendarTimeZone, String userTimeZone,Double utcOffset){
        Date newStartDate = DateUtil.timezoneDateChange(date , calendarTimeZone, userTimeZone);
        Date interNewStartDate = DateUtil.timezoneDateChange(newStartDate ,userTimeZone,System.getProperty("user.timezone") );
        TimeZone chongqing = TimeZone.getTimeZone(userTimeZone);
        Double d = chongqing.getRawOffset()/60/60/1000 - utcOffset;
        return DateUtil.dealWithDate(interNewStartDate, DateConstant.TimeUnit.HOUR,d);
    }

    /**
     *
     * @param date 日期
     * @param calendarTimeZone 日程时区
     * @param userTimeZone 用户时区
     * @param utcOffset 浏览器时差
     * @return
     */
    public static Date dealTimezomeI(Date date ,String calendarTimeZone, String userTimeZone,Double utcOffset){
        Date newStartDate = DateUtil.timezoneDateChange(date , System.getProperty("user.timezone"),userTimeZone );
        Date interNewStartDate = DateUtil.timezoneDateChange(newStartDate ,userTimeZone, calendarTimeZone);
        TimeZone chongqing = TimeZone.getTimeZone(userTimeZone);
        Double d = utcOffset - chongqing.getRawOffset()/60/60/1000;
        return DateUtil.dealWithDate(interNewStartDate,DateConstant.TimeUnit.HOUR,d);
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取年份
     * @return
     */
    public static int getCurrYear(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return currentYear;
    }

    /**
     * 获取日期年份
     * @return
     */
    public static int getCurrYear(Date date){
        if(date == null){
            return 0;
        }
        Calendar currCal=Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        return currentYear;
    }





}