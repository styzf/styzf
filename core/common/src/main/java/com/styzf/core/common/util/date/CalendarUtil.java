package com.styzf.core.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>描述</b>: 日历转换工具类：阴历和阳历日期互换(阴历日期范围19000101~20491229)<br>
 * @author styzf
 * @date 2018年7月25日 
 *
 */
public class CalendarUtil {
    private static final Logger logger = LoggerFactory.getLogger(CalendarUtil.class);
	
	// 计算阴历日期参照1900年到2049年
	private final static int[] LUNAR_INFO = { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0,
			0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
			0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
			0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573,
			0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950,
			0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
			0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970,
			0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50,
			0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
			0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
			0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
			0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };

	// 允许输入的最小年份
	private final static int MIN_YEAR = 1900;
	// 允许输入的最大年份
	private final static int MAX_YEAR = 2049;
	// 当年是否有闰月
	private static boolean isLeapYear;
	// 阳历日期计算起点
	private final static String START_DATE = "19000130";

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 计算阴历 {@code year}年闰哪个月 1-12 , 没闰传回 0
	 * 
	 * @param year
	 *            阴历年
	 * @return (int)月份
	 * @author tangfeng
	 */
	private static int getLeapMonth(int year) {
		return (int) (LUNAR_INFO[year - 1900] & 0xf);
	}

	/**
	 * 计算阴历{@code year}年闰月多少天
	 * 
	 * @param year
	 *            阴历年
	 * @return (int)天数
	 * @author tangfeng
	 */
	private static int getLeapMonthDays(int year) {
		if (getLeapMonth(year) != 0) {
			if ((LUNAR_INFO[year - 1900] & 0xf0000) == 0) {
				return 29;
			} else {
				return 30;
			}
		} else {
			return 0;
		}

	}

	/**
	 * 计算阴历{@code lunarYeay}年{@code month}月的天数
	 * 
	 * @param lunarYeay
	 *            阴历年
	 * @param month
	 *            阴历月
	 * @return (int)该月天数
	 * @throws Exception
	 * @author tangfeng
	 */
	private static int getMonthDays(int lunarYeay, int month) throws Exception {
		if ((month > 31) || (month < 0)) {
			throw (new Exception("月份有错！"));
		}
		// 0X0FFFF[0000 {1111 1111 1111} 1111]中间12位代表12个月，1为大月，0为小月
		int bit = 1 << (16 - month);
		if (((LUNAR_INFO[lunarYeay - 1900] & 0x0FFFF) & bit) == 0) {
			return 29;
		} else {
			return 30;
		}
	}

	/**
	 * 计算阴历{@code year}年的总天数
	 * 
	 * @param year
	 *            阴历年
	 * @return (int)总天数
	 * @author tangfeng
	 */
	private static int getYearDays(int year) {
		int sum = 29 * 12;
		for (int i = 0x8000; i >= 0x8; i >>= 1) {
			if ((LUNAR_INFO[year - 1900] & 0xfff0 & i) != 0) {
				sum++;
			}
		}
		return sum + getLeapMonthDays(year);
	}

	/**
	 * 计算两个阳历日期相差的天数。
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            截至时间
	 * @return (int)天数
	 * @author tangfeng
	 */
	private static int daysBetween(Date startDate, Date endDate) {
		long between_days = (endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 检查阴历日期是否合法
	 * 
	 * @param lunarYear
	 *            阴历年
	 * @param lunarMonth
	 *            阴历月
	 * @param lunarDay
	 *            阴历日
	 * @param leapMonthFlag
	 *            闰月标志
	 * @throws Exception
	 */
	private static void checkLunarDate(int lunarYear, int lunarMonth, int lunarDay, boolean leapMonthFlag)
			throws Exception {
		if ((lunarYear < MIN_YEAR) || (lunarYear > MAX_YEAR)) {
			throw (new Exception("非法农历年份！"));
		}
		if ((lunarMonth < 1) || (lunarMonth > 12)) {
			throw (new Exception("非法农历月份！"));
		}
		if ((lunarDay < 1) || (lunarDay > 30)) { // 中国的月最多30天
			throw (new Exception("非法农历天数！"));
		}

		int leap = getLeapMonth(lunarYear);// 计算该年应该闰哪个月
		if ((leapMonthFlag == true) && (lunarMonth != leap)) {
			throw (new Exception("非法闰月！"));
		}
	}

	/**
	 * 阴历转换为阳历
	 * 
	 * @param lunarDate
	 *            阴历日期
	 * @param leapMonthFlag
	 *            是否为闰月
	 * @return 阳历日期
	 * @throws Exception
	 * @author tangfeng
	 */
	public static Date lunarToSolar(Date lunarDate, boolean leapMonthFlag) throws Exception {
		int lunarYear = lunarDate.getYear();
		int lunarMonth = lunarDate.getMonth() + 1;
		int lunarDay = lunarDate.getDay();

		checkLunarDate(lunarYear, lunarMonth, lunarDay, leapMonthFlag);

		int offset = 0;

		for (int i = MIN_YEAR; i < lunarYear; i++) {
			int yearDaysCount = getYearDays(i); // 求阴历某年天数
			offset += yearDaysCount;
		}
		// 计算该年闰几月
		int leapMonth = getLeapMonth(lunarYear);

		if (leapMonthFlag & leapMonth != lunarMonth) {
			throw (new Exception("您输入的闰月标志有误！"));
		}

		// 当年没有闰月或月份早于闰月或和闰月同名的月份
		if (leapMonth == 0 || (lunarMonth < leapMonth) || (lunarMonth == leapMonth && !leapMonthFlag)) {
			for (int i = 1; i < lunarMonth; i++) {
				int tempMonthDaysCount = getMonthDays(lunarYear, i);
				offset += tempMonthDaysCount;
			}

			// 检查日期是否大于最大天
			if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {
				throw (new Exception("不合法的农历日期！"));
			}
			offset += lunarDay; // 加上当月的天数
		} else {// 当年有闰月，且月份晚于或等于闰月
			for (int i = 1; i < lunarMonth; i++) {
				int tempMonthDaysCount = getMonthDays(lunarYear, i);
				offset += tempMonthDaysCount;
			}
			if (lunarMonth > leapMonth) {
				int temp = getLeapMonthDays(lunarYear); // 计算闰月天数
				offset += temp; // 加上闰月天数

				if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {
					throw (new Exception("不合法的农历日期！"));
				}
				offset += lunarDay;
			} else { // 如果需要计算的是闰月，则应首先加上与闰月对应的普通月的天数
				// 计算月为闰月
				int temp = getMonthDays(lunarYear, lunarMonth); // 计算非闰月天数
				offset += temp;

				if (lunarDay > getLeapMonthDays(lunarYear)) {
					throw (new Exception("不合法的农历日期！"));
				}
				offset += lunarDay;
			}
		}

		Date myDate = formatter.parse(START_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(myDate);
		c.add(Calendar.DATE, offset);
		myDate = c.getTime();
		return myDate;
	}

	/**
	 * 阴历转换为阳历
	 * 
	 * @param lunarDate
	 *            阴历日期,格式YYYYMMDD
	 * @param leapMonthFlag
	 *            是否为闰月
	 * @return 阳历日期,格式：YYYYMMDD
	 * @throws Exception
	 * @author tangfeng
	 */
	public static String lunarToSolar(String lunarDate, boolean leapMonthFlag) throws Exception {
		Date myDate = lunarToSolar(formatter.parse(lunarDate), leapMonthFlag);
		return formatter.format(myDate);
	}

	/**
	 * 阳历日期转换为阴历日期
	 * @param solarDate
	 * @return	农历的年、月、日、是否为闰月
	 * 			int[0],农历年
	 * 			int[1],农历月
	 * 			int[2],农历日
	 * 			int[3],农历是否为闰月
	 * @throws Exception
	 */
	public static int[] solarToLunar(Date solarDate)  throws Exception {
		Date myDate = null;
		Date startDate = null;
		try {
			myDate = solarDate;
			startDate = formatter.parse(START_DATE);
		} catch (ParseException e) {
			logger.error("日期(" + formatter.format(solarDate) + ")格式化出错!", e);
		}
		
		int i;
		int temp = 0;
		int lunarYear = 0;
		int lunarMonth = 0; // 农历月份
		int lunarDay = 0; // 农历当月第几天
		boolean leapMonthFlag = false;

		int leapMonth = 0;
		try {
			int offset = daysBetween(startDate, myDate);
			for (i = MIN_YEAR; i <= MAX_YEAR; i++) {
				temp = getYearDays(i); // 求当年农历年天数
				if (offset - temp < 1) {
					break;
				} else {
					offset -= temp;
				}
			}
			lunarYear = i;

			leapMonth = getLeapMonth(lunarYear);
			// 设定当年是否有闰月
			if (leapMonth > 0) {
				isLeapYear = true;
			} else {
				isLeapYear = false;
			}

			for (i = 1; i <= 12; i++) {
				if (i == leapMonth + 1 && isLeapYear) {
					temp = getLeapMonthDays(lunarYear);
					isLeapYear = false;
					leapMonthFlag = true;
					i--;
				} else {
					temp = getMonthDays(lunarYear, i);
				}
				offset -= temp;
				if (offset <= 0) {
					break;
				}
			}

			offset += temp;
			lunarMonth = i;
			lunarDay = offset;
		} catch (Exception e) {
			logger.error("获取日期(" + formatter.format(solarDate) + ")的农历日期出错!", e);
			throw new Exception("获取日期(" + formatter.format(solarDate) + ")的农历日期出错!", e);
		}
		
		return new int[]{lunarYear, lunarMonth, lunarDay, (leapMonthFlag & (lunarMonth == leapMonth) ? 1 : 0)};
	}
	
	/**
	 * 阳历日期转换为阴历日期
	 * 
	 * @param solarDate
	 *            阳历日期,格式YYYYMMDD
	 * @return 阴历日期.格式2016年12月30日或2016年闰12月30日
	 * @throws Exception
	 * @author tangfeng
	 */
	public static String solarToLunar(String solarDate) throws Exception {
		Date myDate = null;
		try {
			myDate = formatter.parse(solarDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int[] lunars = solarToLunar(myDate);
		return lunars[0] + "年" + ((lunars[3] == 1) ? "闰" : "") + lunars[1] + "月" + lunars[2]
				+ "日";
	}

}