/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.timor.yz.blog.common.Constant;

public class BaseController
{
	/**
	 * @Description 获取ServletRequestAttributes对象
	 * @return ServletRequestAttributes对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:49:21
	 */
	public ServletRequestAttributes getAttributes()
	{
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	/**
	 * @Description 获取HttpServletRequest对象
	 * @return HttpServletRequest对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:49:05
	 */
	public HttpServletRequest getRequest()
	{
		return getAttributes().getRequest();
	}

	/**
	 * @Description 获取HttpServletResponse对象
	 * @return HttpServletResponse对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:48:48
	 */
	public HttpServletResponse getResponse()
	{
		return getAttributes().getResponse();
	}

	/**
	 * @Description 获取HttpSession对象
	 * @return HttpSession对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:48:24
	 */
	public HttpSession getSession()
	{
		return getRequest().getSession();
	}

	/**
	 * @Description 检测验证码的有效性
	 * @param code 需检测的验证码
	 * @param type 操作类型（1：注册、2：登录）
	 * @return 1：正确、0：错误、-1：失效
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午4:26:39
	 */
	public int checkValidateCode(String code, int type)
	{
		String key = type == 1 ? Constant.VERCODE_KEY_REGIST : Constant.VERCODE_KEY_LOGIN;
		Object vercode = getSession().getAttribute(key);
		if (null == vercode)
			return -1;
		if (!code.equalsIgnoreCase(vercode.toString()))
			return 0;
		return 1;
	}
}
