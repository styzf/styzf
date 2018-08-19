package com.styzf.core.common.constant;

/**
 * 通用的常量
 * @author styzf
 * @date 2018年7月27日 
 *
 */
public interface CommonConstant {
    /**
     * 逗号,
     */
    public static final String COMMA = ",";
    /**
     * 分号;
     */
    public static final String SEMICOLON = ";";
    /**
     * 空格
     */
    public static final String SPACE = " ";

    public static final String YES = "Y";

    public static final String NO = "N";

    public static final String ALL = "A";

    public static final Integer NUM_YES = 1;
    
    public static final Integer NUM_NO = 0;

    public static final String UNDER_LINE = "_";
    
    public interface ROLE {
        public static final String ADMIN = "admin";
        public static final String VIP0 = "VIP0";
        public static final String VIP1 = "VIP1";
        public static final String VIP2 = "VIP2";
        public static final String VIP3 = "VIP3";
        public static final String VIP4 = "VIP4";
        public static final String VIP5 = "VIP5";
        public static final String VIP6 = "VIP6";
        public static final String VIP7 = "VIP7";
        public static final String VIP8 = "VIP8";
        public static final String VIP9 = "VIP9";
    }
    
    public interface LANG {

        public static final String ZH = "zh-CN";

        public static final String EN = "en-US";
        
        public static final String JP = "ja-JP";
    }

    /**
     *  接口处理状态
     */
    public interface ProcessStatus {
        /**
         * 未处理<br>N
         */
        public static final String N = "N";
        /**
         * 处理中<br>P
         */
        public static final String P = "P";
        /**
         * 处理失败<br>E
         */
        public static final String E = "E";
        /**
         * 处理成功<br>S
         */
        public static final String S = "S";
    }
}
