package com.timor.utils;

/**
 * @Description
 *              <ul>
 *              系统级调用工具类，继承org.apache.commons.lang3.SystemUtils
 *              <hr>
 *              <li>commons-lang3中SystemUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79137910</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月26日 上午9:18:38
 */
public class SystemUtils extends org.apache.commons.lang3.SystemUtils
{
	public static void main(String[] args)
	{
		System.out.println(getUserDir());
	}
}
