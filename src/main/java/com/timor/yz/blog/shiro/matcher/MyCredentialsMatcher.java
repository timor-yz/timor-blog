/**
 * timor-yz所有
 */
package com.timor.yz.blog.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 自定义凭证（密码）认证器
 * @author YuanZhe
 * @date 2018年12月24日 上午10:09:50
 * 
 */
public class MyCredentialsMatcher extends SimpleCredentialsMatcher
{
	private static final Logger logger = LoggerFactory.getLogger(MyCredentialsMatcher.class);

	/**
	 * @Fields HASH_ALGORITHM_NAME : 加密算法名称
	 */
	public static final String HASH_ALGORITHM_NAME = "MD5";

	/**
	 * @Fields HASH_ITERATIONS : 加密次数
	 */
	public static final int HASH_ITERATIONS = 1024;

	/**
	 * @Description 自定义凭证（密码）认证方法
	 * @param token AuthenticationToken对象
	 * @param info  AuthenticationInfo对象
	 * @return 验证结果（true：匹配、false：不匹配）
	 * 
	 * @author YuanZhe
	 * @date 2018年12月24日 上午10:11:13
	 */
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		logger.info("---> 进行凭证认证");
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 将用户输入的密码进行加密
		String pwdStr = String.valueOf(upToken.getPassword());// 所需加密的参数（用户输入的密码）
		String salt = upToken.getUsername();// [盐] 一般为用户名 或 随机数
		SimpleHash simpleHash = new SimpleHash(HASH_ALGORITHM_NAME, pwdStr, salt, HASH_ITERATIONS);
		String inputPwd = simpleHash.toHex();
		logger.info("-- 待认证密码为 : {}", inputPwd);

		// 获得正确密码（数据库所存储密码）
		String dbPwd = (String) getCredentials(info);
		logger.info("-- 正确密码为 : {}", dbPwd);

		boolean res = this.equals(inputPwd, dbPwd);// 进行密码的比对
		logger.info("---> 凭证认证结果 : {}", String.valueOf(res));

		return res;
	}
	
	public static void main(String[] args)
	{
		SimpleHash pwdHash = new SimpleHash(HASH_ALGORITHM_NAME, "///13579zse", "admin", HASH_ITERATIONS);
		System.out.println(pwdHash.toString());
	}
}
