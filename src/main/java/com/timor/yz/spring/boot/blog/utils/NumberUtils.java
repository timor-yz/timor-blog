package com.timor.yz.spring.boot.blog.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @Description
 *              <ul>
 *              数字工具类，继承org.apache.commons.lang3.math.NumberUtils
 *              <hr>
 *              <li>commons-lang3中NumberUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79132653</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月10日 下午3:37:06
 * 
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils
{
	/**
	 * @Fields REG_NUMBER : 正则-数字
	 */
	public static final String REG_NUMBER = "^([-]|[+])?\\d+([.]\\d+)?$";

	/**
	 * @Fields REG_INTEGER : 正则-整数
	 */
	public static final String REG_INTEGER = "^([-]|[+])?\\d+$";

	/**
	 * @Fields REG_DECIMAL : 正则-小数
	 */
	public static final String REG_DECIMAL = "^([-]|[+])?\\d+[.]\\d+$";

	/**
	 * @Fields REG_POSITIVE : 正则-正数
	 */
	public static final String REG_POSITIVE = "^[+]?\\d+([.]\\d+)?$";

	/**
	 * @Fields REG_POSIT_INT : 正则-正整数
	 */
	public static final String REG_POSIT_INT = "^[+]?\\d+$";

	/**
	 * @Description 判断当前值是否为数字
	 * @param str 需判断的值
	 * @return true：是、false：否
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午10:46:24
	 */
	public static boolean isDigits(Object obj)
	{
		if (null == obj)
			return false;
		return isDigits(obj.toString());
	}

	/**
	 * @Description 判断字符串是否为整数
	 * @param str 需判断的字符串
	 * @return true：是、false：否
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:01:26
	 */
	public static boolean isInteger(String str)
	{
		if (StringUtils.isEmpty(str))
			return false;
		return Pattern.matches(REG_INTEGER, str);
	}

	/**
	 * @Description 判断字符串是否为小数
	 * @param str 需判断的字符串
	 * @return true：是、false：否
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:01:26
	 */
	public static boolean isDecimal(String str)
	{
		if (StringUtils.isEmpty(str))
			return false;
		return Pattern.matches(REG_DECIMAL, str);
	}

	/**
	 * @Description 判断字符串是否为正数
	 * @param str 需判断的字符串
	 * @return true：是、false：否
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:01:26
	 */
	public static boolean isPositive(String str)
	{
		if (StringUtils.isEmpty(str))
			return false;
		return Pattern.matches(REG_POSITIVE, str);
	}

	/**
	 * @Description 判断字符串是否为正整数
	 * @param str 需判断的字符串
	 * @return true：是、false：否
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:12:07
	 */
	public static boolean isPositInt(String str)
	{
		if (StringUtils.isEmpty(str))
			return false;
		return Pattern.matches(REG_POSIT_INT, str);
	}

	/**
	 * @Description 将数字格式化输出
	 * @param str       需要格式化的值
	 * @param precision 精度（小数点后的位数）（默认2位）
	 * @return 格式化后的数字字符串
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:18:02
	 */
	public static String format(String str, Integer precision)
	{
		Double number = 0.0;
		if (isDigits(str))
			number = new Double(str);
		precision = (precision == null || precision < 0) ? 2 : precision;
		BigDecimal bigDecimal = new BigDecimal(number);
		return bigDecimal.setScale(precision, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * @Description 将数字格式化输出（保留小数点后2位）
	 * @param str 需要格式化的值
	 * @return 格式化后的数字字符串
	 * 
	 * @author YuanZhe
	 * @date 2018年8月14日 上午11:26:35
	 */
	public static String format(String str)
	{
		return format(str, 2);
	}

	public static void main(String[] args)
	{

	}
}
