/**
 * timor-yz所有
 */
package com.timor.yz.blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description 请求对象获取工具类
 * @author YuanZhe
 * @date 2018年12月28日 上午10:50:08
 * 
 */
public class RequestUtils
{
	/**
	 * @Description 获取ServletRequestAttributes对象
	 * @return ServletRequestAttributes对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:50:08
	 */
	public static ServletRequestAttributes getAttributes()
	{
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	/**
	 * @Description 获取HttpServletRequest对象
	 * @return HttpServletRequest对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:50:08
	 */
	public static HttpServletRequest getRequest()
	{
		return getAttributes().getRequest();
	}

	/**
	 * @Description 获取HttpServletResponse对象
	 * @return HttpServletResponse对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:50:08
	 */
	public static HttpServletResponse getResponse()
	{
		return getAttributes().getResponse();
	}

	/**
	 * @Description 获取HttpSession对象
	 * @return HttpSession对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午10:50:08
	 */
	public static HttpSession getSession()
	{
		return getRequest().getSession();
	}
}
