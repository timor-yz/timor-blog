package com.timor.yz.spring.boot.blog.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 *              <ul>
 *              String工具类，继承org.apache.commons.lang3.StringUtils
 *              <hr>
 *              <li>commons-lang3中StringUtils类方法介绍参考：
 *              <ol>
 *              <li>官方PAI
 *              <ul>
 *              <li><i>http://commons.apache.org/proper/commons-lang/javadocs/api-release/</i></li>
 *              </ul>
 *              </li>
 *              <li>“随风yy”提供
 *              <ul>
 *              <li>字符串拼接(join)详解：<i>https://blog.csdn.net/yaomingyang/article/details/79154947</i></li>
 *              <li>isEmpty、isBlank、isNotEmpty、isNotBlank方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79165123</i></li>
 *              <li>trim、trimToEmpty、trimToNull方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79166024</i></li>
 *              <li>strip、stripStart、stripEnd剥离方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79169547</i></li>
 *              <li>剔除字符串末回车换行符（\r\n）chomp、chop详解：<i>https://blog.csdn.net/yaomingyang/article/details/79172988</i></li>
 *              <li>isAlpha、isAlphanumeric、isAlphanumericSpace、isAlphaSpace方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79263822</i></li>
 *              <li>defaultString、defaultIfBlank、defaultIfEmpty方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79264079</i></li>
 *              <li>equals、equalsIgnoreCase、equalsAny、equalsAnyIgnoreCase方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79270336</i></li>
 *              <li>compare、compareIgnoreCase方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79270581</i></li>
 *              <li>replace、replaceEach、replaceEachRepeatedly、replaceFirst方法详解：<i>https://blog.csdn.net/yaomingyang/article/details/79273849</i></li>
 *              </ul>
 *              </li>
 *              <li>“|一只想飞的猪|”提供
 *              <ul>
 *              <li>判空、转换、移除、替换、反转：<i>https://www.cnblogs.com/guiblog/li/7787569.html</i></li>
 *              <li>截取、去除空白、包含、查询索引：<i>https://www.cnblogs.com/guiblog/li/7986410.html</i></li>
 *              </ul>
 *              </li>
 *              </ol>
 *              </li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月4日 下午2:49:07
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
	public static void main(String[] args)
	{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("key1", "value1");
		map1.put("key2", "value2");
		map1.put("key3", "value3");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("key4", "value4");
		map2.put("key5", "value5");
		map2.put("key6", "value6");
		list.add(map1);
		list.add(map2);
		System.out.println(join(list));
	}
}