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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.timor.yz.blog.service.UserService;

/**
 * @Description 用户注册处理控制器
 * @author YuanZhe
 * @date 2018年8月30日 下午4:24:19
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/doLogin")
	@ResponseBody
	public ModelAndView doLogin(Model model)
	{
		logger.info("用户登录...");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("", "");
		try
		{
			subject.login(token);
		}catch (AuthenticationException e)
		{
			token.clear();
			return new ModelAndView();
		}
		return new ModelAndView();
	}

	@RequestMapping("/regist")
	@ResponseBody
	public ModelAndView regist(Model model)
	{
		logger.info("用户登录...");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("", "");
		try
		{
			subject.login(token);
		}catch (AuthenticationException e)
		{
			token.clear();
			return new ModelAndView();
		}
		return new ModelAndView();
	}
}
