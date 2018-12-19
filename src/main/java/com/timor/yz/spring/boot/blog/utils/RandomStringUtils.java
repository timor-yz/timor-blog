package com.timor.yz.spring.boot.blog.utils;

/**
 * @Description
 *              <ul>
 *              随机字符串工具类，继承org.apache.commons.lang3.RandomStringUtils
 *              <hr>
 *              <li>commons-lang3中RandomStringUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79107764</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月10日 下午3:35:42
 * 
 */
public class RandomStringUtils extends org.apache.commons.lang3.RandomStringUtils
{
	/**
	 * @Description 获取指定位数由字母、数字组成的主键id
	 * @param digit 位数
	 * @return 由字母、数字组成的随机数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 下午2:56:22
	 */
	public static String getUUID(int digit)
	{
		return random(digit, true, true);
	}

	/**
	 * @Description 获取由字母、数字组成的20位主键id
	 * @return 主键id
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 下午2:53:40
	 */
	public static String getUUID()
	{
		return getUUID(20);
	}

	public static void main(String[] args)
	{
		String str = random(20, true, true);
		System.out.println(str);
		System.out.println(str.toUpperCase());
	}
}