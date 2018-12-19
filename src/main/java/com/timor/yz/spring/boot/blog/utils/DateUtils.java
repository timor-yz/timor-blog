package com.timor.yz.spring.boot.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @Description
 *              <ul>
 *              日期时间工具类，继承org.apache.commons.lang3.time.DateUtils
 *              <hr>
 *              <li>commons-lang3中DateUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79138466</i></li>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79142201</i></li>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79143651</i></li>
 *              </ul>
 *              </li>
 *              <li>“aston”提供
 *              <ul>
 *              <li><i>https://www.cnblogs.com/aston/p/9053201.html</i></li>
 *              </ul>
 *              </li>
 *              <li>“阿旺_liu”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yihaoawang/article/details/50638199</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月10日 下午3:39:01
 * 
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
	/**
	 * @Fields FMT_DEFAULT : 格式-默认（yyyy-MM-dd HH:mm:ss）
	 */
	public static final String FMT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @Fields FMT_DEFAULT_SLASH : 格式（yyyy/MM/dd HH:mm:ss）
	 */
	public static final String FMT_DEFAULT_SLASH = "yyyy/MM/dd HH:mm:ss";
	/**
	 * @Fields FMT_DEFAULT_NONE : 格式（yyyyMMddHHmmss）
	 */
	public static final String FMT_DEFAULT_NONE = "yyyyMMddHHmmss";

	/**
	 * @Fields FMT_DEFAULT : 格式（yyyy-MM-dd HH:mm:ss.SSS）
	 */
	public static final String FMT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * @Fields FMT_DEFAULT : 格式（yyyy/MM/dd HH:mm:ss.SSS）
	 */
	public static final String FMT_FULL_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";

	/**
	 * @Fields FMT_DEFAULT : 格式（yyyyMMddHHmmssSSS）
	 */
	public static final String FMT_FULL_NONE = "yyyyMMddHHmmssSSS";

	/**
	 * @Fields FMT_DATE : 格式-日期（yyyy-MM-dd）
	 */
	public static final String FMT_DATE = "yyyy-MM-dd";

	/**
	 * @Fields FMT_DATE_SLASH : 格式-日期（yyyy/MM/dd）
	 */
	public static final String FMT_DATE_SLASH = "yyyy/MM/dd";

	/**
	 * @Fields FMT_DATE_NONE : 格式-日期（yyyyMMdd）
	 */
	public static final String FMT_DATE_NONE = "yyyyMMdd";

	/**
	 * @Fields FMT_YEAR : 格式-年（yyyy）
	 */
	public static final String FMT_YEAR = "yyyy";

	/**
	 * @Fields FMT_YEAR_MON : 格式-年月（yyyy-MM）
	 */
	public static final String FMT_YEAR_MON = "yyyy-MM";

	/**
	 * @Fields FMT_YEAR_MON_SLASH : 格式-年月（yyyy/MM）
	 */
	public static final String FMT_YEAR_MON_SLASH = "yyyy/MM";

	/**
	 * @Fields FMT_YEAR_MON_NONE : 格式-年月（yyyyMM）
	 */
	public static final String FMT_YEAR_MON_NONE = "yyyyMM";

	/**
	 * @Fields FMT_TIME : 格式-时间（HH:mm:ss）
	 */
	public static final String FMT_TIME = "HH:mm:ss";

	/**
	 * @Description 根据指定格式获取SimpleDateFormat对象
	 * @param pattern 格式
	 * @return SimpleDateFormat对象
	 * 
	 * @author YuanZhe
	 * @date 2018年8月13日 下午5:50:34
	 */
	public static SimpleDateFormat getSdf(String pattern)
	{
		return new SimpleDateFormat(pattern);
	}

	/**
	 * @Description 根据固定格式获取SimpleDateFormat对象（yyyy-MM-dd HH:mm:ss）
	 * @return SimpleDateFormat对象
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 下午2:08:54
	 */
	public static SimpleDateFormat getSdf()
	{
		return getSdf(FMT_DEFAULT);
	}

	/**
	 * @Description 根据固定格式获取当前时间字符串
	 * @param pattern 格式（为空则返回默认格式：yyyy-MM-dd HH:mm:ss）
	 * @return 当前时间字符串
	 * 
	 * @author YuanZhe
	 * @date 2018年8月8日 上午11:41:27
	 */
	public static String getCurrDateStr(String pattern)
	{
		return DateFormatUtils.format(new Date(), StringUtils.isBlank(pattern) ? pattern : FMT_DEFAULT);
	}

	/**
	 * @Description 获取当前时间字符串
	 * @return 当前时间字符串（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @author YuanZhe
	 * @date 2018年8月8日 上午11:37:36
	 */
	public static String getCurrDateStr()
	{
		return getCurrDateStr(FMT_DEFAULT);
	}

	/**
	 * @Description 获取当前时间毫秒数
	 * @return 当前日期毫秒数
	 * 
	 * @author YuanZhe
	 * @date 2018年8月13日 下午5:53:01
	 */
	public static long nowTimeMillis()
	{
		return System.currentTimeMillis();
	}

	/**
	 * @Description 计算（date2-date1）之间的相差毫秒数
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差毫秒数
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:00:16
	 */
	public static Integer diffMillis(Date date1, Date date2)
	{
		if (null == date1 || null == date2)
			return null;
		return (int) (date2.getTime() - date1.getTime());
	}

	/**
	 * @Description 计算（date2-date1）之间的相差绝对毫秒数（正数）
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差绝对毫秒数（正数）
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:00:16
	 */
	public static Integer diffAbsMillis(Date date1, Date date2)
	{
		if (null == date1 || null == date2)
			return null;
		return Math.abs(diffMillis(date1, date2));
	}

	/**
	 * @Description 计算（date2-date1）之间的相差天数（去除精度）
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差天数（去除精度）
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:09:29
	 */
	public static Integer diffDays(Date date1, Date date2)
	{
		Integer millis = diffMillis(date1, date2);
		if (millis == null)
			return null;
		return millis / (1000 * 60 * 60 * 24);
	}

	/**
	 * @Description 计算（date2-date1）之间的相差月数（去除精度）
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差月数（去除精度）
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:32:24
	 */
	public static Integer diffMonths(Date date1, Date date2)
	{
		if (null == date1 || null == date2)
			return null;
		return diffYears(date1, date2) * 12 + toCalendar(date2).get(Calendar.MONTH)
				- toCalendar(date1).get(Calendar.MONTH);
	}

	/**
	 * @Description 计算（date2-date1）之间的相差年数（去除精度）
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 相差年数（去除精度）
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:09:29
	 */
	public static Integer diffYears(Date date1, Date date2)
	{
		if (null == date1 || null == date2)
			return null;
		return toCalendar(date2).get(Calendar.YEAR) - toCalendar(date1).get(Calendar.YEAR);
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(parseDate("20170501", new String[] { "yyyy-MM-dd", "yyyyMMdd" }));
	}

}
