/**
 * timor-yz所有
 */
package com.timor.yz.blog.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description 异常处理
 * @author YuanZhe
 * @date 2018年12月29日 上午11:23:26
 * 
 */
@Component
public class MyErrorViewResolver implements ErrorViewResolver
{
	private static final Logger logger = LoggerFactory.getLogger(MyErrorViewResolver.class);

	/**
	 * @Description 异常处理
	 * @param request HttpServletRequest对象
	 * @param status  HttpStatus对象
	 * @param model   Map<String, Object>对象
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年12月29日 上午11:23:26
	 */
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model)
	{
		logger.info("----------> 拦截异常");
		logger.info("---> 异常状态代码 : {}", model.get("status"));
		logger.info("---> 异常路径 : {}", model.get("path"));
		logger.info("---> 异常信息 : {}", model.get("message"));

		// 404
		if (HttpStatus.NOT_FOUND == status)
			return new ModelAndView("redirect:/web/index");

		// 服务器异常
		if (status.is5xxServerError())
			return new ModelAndView("redirect:/toServerError");

		return new ModelAndView("redirect:/web/index");
	}
}
