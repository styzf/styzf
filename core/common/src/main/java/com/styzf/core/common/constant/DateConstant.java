package com.styzf.core.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Date相关的部分常量
 * @author styzf
 * @date 2018年7月25日 
 *
 */
public class DateConstant {
    /**
     * 月份，多语言，默认中文
     */
    public static final Map<String, String> monthMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            put("1" , "一月" );
            put("2" , "二月" );
            put("3" , "三月" );
            put("4" , "四月" );
            put("5" , "五月" );
            put("6" , "六月" );
            put("7" , "七月" );
            put("8" , "八月" );
            put("9" , "九月" );
            put("10", "十月" );
            put("11", "十一月");
            put("12", "十二月");

            put(1  + CommonConstant.LANG.ZH, "一月" );
            put(2  + CommonConstant.LANG.ZH, "二月" );
            put(3  + CommonConstant.LANG.ZH, "三月" );
            put(4  + CommonConstant.LANG.ZH, "四月" );
            put(5  + CommonConstant.LANG.ZH, "五月" );
            put(6  + CommonConstant.LANG.ZH, "六月" );
            put(7  + CommonConstant.LANG.ZH, "七月" );
            put(8  + CommonConstant.LANG.ZH, "八月" );
            put(9  + CommonConstant.LANG.ZH, "九月" );
            put(10 + CommonConstant.LANG.ZH, "十月" );
            put(11 + CommonConstant.LANG.ZH, "十一月");
            put(12 + CommonConstant.LANG.ZH, "十二月");
            
            put(1  + CommonConstant.LANG.EN, "January"  );
            put(2  + CommonConstant.LANG.EN, "February" );
            put(3  + CommonConstant.LANG.EN, "March"    );
            put(4  + CommonConstant.LANG.EN, "April"    );
            put(5  + CommonConstant.LANG.EN, "May"      );
            put(6  + CommonConstant.LANG.EN, "June"     );
            put(7  + CommonConstant.LANG.EN, "July"     );
            put(8  + CommonConstant.LANG.EN, "August"   );
            put(9  + CommonConstant.LANG.EN, "September");
            put(10 + CommonConstant.LANG.EN, "October"  );
            put(11 + CommonConstant.LANG.EN, "November" );
            put(12 + CommonConstant.LANG.EN, "December" );
                     
            put(1  + CommonConstant.LANG.JP, "一月" );
            put(2  + CommonConstant.LANG.JP, "二月" );
            put(3  + CommonConstant.LANG.JP, "三月" );
            put(4  + CommonConstant.LANG.JP, "四月" );
            put(5  + CommonConstant.LANG.JP, "五月" );
            put(6  + CommonConstant.LANG.JP, "六月" );
            put(7  + CommonConstant.LANG.JP, "七月" );
            put(8  + CommonConstant.LANG.JP, "八月" );
            put(9  + CommonConstant.LANG.JP, "九月" );
            put(10 + CommonConstant.LANG.JP, "十月" );
            put(11 + CommonConstant.LANG.JP, "十一月");
            put(12 + CommonConstant.LANG.JP, "十二月");
        }
    };
	/**
	 * 阳历-阴历月份对照
	 */
	public static Map<Integer, String> reflectChineseMonths = new HashMap<Integer, String>(){
        private static final long serialVersionUID = 1L;

        {
		    put(1, "正月");
		    put(2, "二月");
		    put(3, "三月");
		    put(4, "四月");
		    put(5, "五月");
		    put(6, "六月");
		    put(7, "七月");
		    put(8, "八月");
		    put(9, "九月");
		    put(10, "十月");
		    put(11, "十一月");
		    put(12, "十二月");
	    }};
	/**
	 * 阳历-阴历日期对照
	 */
	public static Map<Integer, String> reflectChineseDays = new HashMap<Integer, String>(){
		private static final long serialVersionUID = -5593518202741784776L;
		{
		    put(1, "初一");
		    put(2, "初二");
		    put(3, "初三");
		    put(4, "初四");
		    put(5, "初五");
		    put(6, "初六");
		    put(7, "初七");
		    put(8, "初八");
		    put(9, "初九");
		    put(10, "初十");
		    put(11, "十一");
		    put(12, "十二");
		    put(13, "十三");
		    put(14, "十四");
		    put(15, "十五");
		    put(16, "十六");
		    put(17, "十七");
		    put(18, "十八");
		    put(19, "十九");
		    put(20, "二十");
		    put(21, "廿一");
		    put(22, "廿二");
		    put(23, "廿三");
		    put(24, "廿四");
		    put(25, "廿五");
		    put(26, "廿六");
		    put(27, "廿七");
		    put(28, "廿八");
		    put(29, "廿九");
		    put(30, "三十");
		    put(31, "三十一");
	}};

    public interface Common {
        public static final String CHINAZONE = "Asia/Shanghai";
        
        public static final String ZERO_TIME_ZONE = "UTC";

        public static final String SUCCESS = "SUCCESS";
    }

    
    /**
     *	Kafka Topics
     */
    public interface KafkaTopics {

    }
    
    /**
     *	日程提醒时间单位
     */
    public interface TimeUnit {
        /**
         * 分钟<br>MINUTE
         */
        public static final String MINUTE = "MINUTE";
        /**
         * 小时<br>HOUR
         */
        public static final String HOUR = "HOUR";
        /**
         * 天<br>DAY
         */
        public static final String DAY = "DAY";
    }


    private static final String DAY = "DAY";
    private static final String WEEK = "WEEK";
    private static final String SUN = "SUN";
    private static final String MON = "MON";
    private static final String TUES = "TUES";
    private static final String WED = "WED";
    private static final String THUR = "THUR";
    private static final String FRI = "FRI";
    private static final String SAT = "SAT";
    private static final String WEEKDAY = "WEEKDAY";
    private static final String WEEKENDDAY = "WEEKENDDAY";
    
}
