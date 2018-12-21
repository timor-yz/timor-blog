/**
 * timor-yz所有
 */
package com.timor.yz.blog.utils;

import java.util.Date;

/**
 * @Description
 *              <ul>
 *              日期时间格式化工具类，继承org.apache.commons.lang3.time.DateFormatUtils
 *              <hr>
 *              <li>commons-lang3中DateFormatUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79143954</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年8月16日 上午10:31:45
 * 
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils
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
	 * @Fields FMT_FULL : 格式（yyyy-MM-dd HH:mm:ss.SSS）
	 */
	public static final String FMT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * @Fields FMT_FULL_SLASH : 格式（yyyy/MM/dd HH:mm:ss.SSS）
	 */
	public static final String FMT_FULL_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";

	/**
	 * @Fields FMT_FULL_NONE : 格式（yyyyMMddHHmmssSSS）
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
	 * @Description 获取当前时间【yyyy-MM-dd HH:mm:ss】格式字符串
	 * @return 当前时间【yyyy-MM-dd HH:mm:ss】格式字符串
	 * 
	 * @author YuanZhe
	 * @date 2018年12月20日 下午3:46:35
	 */
	public static String getCurrDateDefStr()
	{
		return getDateDefStr(new Date());
	}

	/**
	 * @Description 根据传入的Date对象获取【yyyy-MM-dd HH:mm:ss】格式字符串
	 * @param date Date对象
	 * @return 【yyyy-MM-dd HH:mm:ss】格式字符串；若date为null，则返回null
	 * 
	 * @author YuanZhe
	 * @date 2018年12月20日 下午3:42:50
	 */
	public static String getDateDefStr(Date date)
	{
		return date == null ? null : format(date, FMT_DEFAULT);
	}

	public static void main(String[] args)
	{

	}
}
