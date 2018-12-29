/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.utils.StringUtils;

/**
 * @Description 用户登录处理控制器
 * @author YuanZhe
 * @date 2018年8月30日 下午4:24:19
 * 
 */
@Controller
public class LoginController
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * @Description 用户登录
	 * @param user 登录用户信息对象
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月25日 上午9:30:52
	 */
	@RequestMapping("login")
	public ModelAndView login(User user)
	{
		logger.info("用户登录...login! user : {}", user == null ? null : user.toString());

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
		{
			logger.info("当前用户已登录，前往首页！");
			return new ModelAndView("redirect:/web/index");// 已登录，前往首页
		}

		if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()))
		{
			logger.info("---> 登录请求信息为空！");
			return new ModelAndView("redirect:/tologin");// 登录请求信息为空，前往登录页面
		}

		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try
		{
			subject.login(token);
		} catch (AuthenticationException e)
		{
			token.clear();
			return new ModelAndView("redirect:/tologin");
		}
		return new ModelAndView("redirect:/web/index");
	}

	/**
	 * @Description 跳转登录页面
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月25日 上午9:30:46
	 */
	@RequestMapping("tologin")
	public ModelAndView tologin()
	{
		logger.info("跳转登录页面...tologin");
		if (SecurityUtils.getSubject().isAuthenticated())
		{
			logger.info("当前用户已登录，前往首页！");
			return new ModelAndView("redirect:/web/index");// 已登录，前往首页
		}
		return new ModelAndView("login");
	}

	/**
	 * @Description 跳转首页
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月25日 上午9:29:58
	 */
	@RequestMapping("web/index")
	public ModelAndView webindex()
	{
		logger.info("跳转首页...web/index");
		return new ModelAndView("index");
	}

	/**
	 * @Description 用户无权限，跳转无权限提示页面
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月25日 上午9:30:16
	 */
	@RequestMapping("unauthorized")
	public ModelAndView unauthorized()
	{
		logger.info("用户无权限，跳转无权限提示页面...unauthorized");
		return new ModelAndView("unauthorized");
	}
}
