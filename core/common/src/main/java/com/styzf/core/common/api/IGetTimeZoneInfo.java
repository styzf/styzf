package com.styzf.core.common.api;

import java.util.TimeZone;

/**
 * 该接口由基础框架提供，时区参数
 * @author styzf
 * @date 2018年7月28日 
 *
 */
public interface IGetTimeZoneInfo {
    /**
     * 获取时区参数
     * @author styzf
     * @date 2018年7月28日 
     * @return
     */
    public abstract TimeZone getTimeZone();
}
