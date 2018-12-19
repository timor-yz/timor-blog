
package com.timor.yz.spring.boot.blog.utils;

/**
 * @Description
 *              <ul>
 *              随机数工具类，继承org.apache.commons.lang3.RandomUtils
 *              <hr>
 *              <li>commons-lang3中RandomUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li><i>https://blog.csdn.net/yaomingyang/article/details/79113376</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月10日 下午3:36:24
 * 
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils
{
	public static void main(String[] args)
	{
		System.out.println(RandomUtils.nextInt(1000, 9999));
	}
}
