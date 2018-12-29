/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 公用处理控制器
 * @author YuanZhe
 * @date 2018年12月29日 上午11:55:34
 * 
 */
@Controller
public class CommonController
{
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	/**
	 * @Description 服务器异常跳转页面
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月29日 上午11:57:41
	 */
	@RequestMapping("toServerError")
	public ModelAndView toServerError()
	{
		logger.info("服务器异常跳转页面...toServerError");
		return new ModelAndView("error/server-error");
	}
}
