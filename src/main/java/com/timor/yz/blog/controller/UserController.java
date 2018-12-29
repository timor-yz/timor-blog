/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.service.UserService;

/**
 * @Description 用户注册处理控制器
 * @author YuanZhe
 * @date 2018年8月30日 下午4:24:19
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView list(Model model)
	{
		logger.info("查询所有用户信息");
		List<User> users = null;// TODO
		model.addAttribute("users", users);
		return new ModelAndView("user/list", "model", model);
	}
}
