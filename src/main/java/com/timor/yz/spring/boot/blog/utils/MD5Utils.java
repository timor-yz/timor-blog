package com.timor.yz.spring.boot.blog.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: MD5工具类
 * @author YuanZhe
 * @date 2018年9月10日 下午3:33:57
 * 
 */
public class MD5Utils
{
	/**
	 * @Description: 将源字符串通过MD5进行加密为字节数组
	 * @param source 源字符串
	 * @return byte[] 通过MD5进行加密后的字节数组
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午3:34:08
	 */
	public static byte[] encodeToBytes(String source)
	{
		byte[] result = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();// 重置
			md.update(source.getBytes("UTF-8"));// 添加需要加密的源
			result = md.digest();// 加密
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Description: 将源字符串通过MD5加密成32位16进制数
	 * @param source 源字符
	 * @return 通过MD5加密成32位16进制数后的字符串
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午3:34:21
	 */
	public static String encodeToHex(String source)
	{
		byte[] data = encodeToBytes(source);// 先加密为字节数组
		StringBuffer hexSb = new StringBuffer();
		for (int i = 0; i < data.length; i++)
		{
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1)
			{
				hexSb.append("0");
			}
			hexSb.append(hex);
		}
		return hexSb.toString();
	}

	/**
	 * @Description: 验证字符串是否匹配
	 * @param unknown 待验证的字符串
	 * @param okHex   使用MD5加密后的16进制字符串
	 * @return 是否匹配
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午3:34:36
	 */
	public static boolean validate(String unknown, String okHex)
	{
		return okHex.equals(encodeToHex(unknown));
	}

	public static void main(String[] args)
	{
		String email = "dreamlandwang@163.com";
		String password = "123456";
		String s = encodeToHex(email + password);
		System.out.println(s);

		boolean validate = validate(email + password, s);
		System.out.println(validate);

		String em = encodeToHex("123456");
		System.out.println(em);
	}
}