/**
 * timor-yz所有
 */
package com.timor.yz.blog.common.validcode;

import com.timor.yz.blog.utils.RequestUtils;
import com.timor.yz.blog.utils.StringUtils;

/**
 * @Description 验证码处理工具类
 * @author YuanZhe
 * @date 2018年12月28日 上午10:45:56
 * 
 */
public class CaptchaUtils
{
	/**
	 * @Description 获取验证码
	 * @param key session中存储验证码对应的key
	 * @return 验证码
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:53:51
	 */
	public static String getValidCode(String key)
	{
		return StringUtils.isBlank(key) ? "" : (String) RequestUtils.getSession().getAttribute(key);
	}

	/**
	 * @Description 检测验证码的有效性
	 * @param code 需检测的验证码
	 * @param key  session中存储验证码对应的key
	 * @return 1：正确、0：错误、-1：失效
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:46:19
	 */
	public static int checkValidateCode(String code, String key)
	{
		String vercode = getValidCode(key);
		if (null == vercode)
			return -1;
		if (!code.equalsIgnoreCase(vercode.toString()))
			return 0;
		return 1;
	}
}
