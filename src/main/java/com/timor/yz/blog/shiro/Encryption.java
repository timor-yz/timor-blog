/**
 * timor-yz所有
 */
package com.timor.yz.blog.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description Shiro密码加密
 * @author YuanZhe
 * @date 2018年12月26日 下午3:35:36
 * 
 */
public class Encryption
{
	/**
	 * @Fields HASH_ALGORITHM_NAME : 加密算法名称
	 */
	public static final String HASH_ALGORITHM_NAME = "MD5";

	/**
	 * @Fields HASH_ITERATIONS : 加密次数
	 */
	public static final int HASH_ITERATIONS = 1024;

	/**
	 * @Description 密码加密
	 * @param pwd  原密码
	 * @param salt [盐] 一般为用户名 或 随机数
	 * @return 加密后的密码
	 * 
	 * @author YuanZhe
	 * @date 2018年12月26日 下午3:31:47
	 */
	public static String pwdEncrypt(String pwd, String salt)
	{
		SimpleHash simpleHash = new SimpleHash(HASH_ALGORITHM_NAME, pwd, salt, HASH_ITERATIONS);
		return simpleHash.toHex();
	}

	public static void main(String[] args)
	{
		System.out.println(pwdEncrypt("///13579zse", "admin"));
	}
}
