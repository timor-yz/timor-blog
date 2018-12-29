package com.timor.yz.blog.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 自定义访问权限配置
 * @author YuanZhe
 * @date 2018年12月28日 下午3:18:25
 * 
 */
public class MyFilterChainDefinition
{

	/**
	 * @Description 获取访问权限配置（anon：可匿名访问、authc：需认证（登录）后才可访问）
	 * @return 访问权限配置Map
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 下午3:18:39
	 */
	public static Map<String, String> getFilterChainDefinitionMap()
	{
		Map<String, String> map = new LinkedHashMap<>();

		/*-- 可匿名访问 --*/
		// 静态资源
		map.put("/js/**", "anon");
		map.put("/css/**", "anon");
		map.put("/imgs/**", "anon");
		map.put("/layui/**", "anon");
		map.put("/common/**", "anon");

		// 注册
		map.put("/regist/**", "anon");
		map.put("/captchaServlet", "anon");

		// 登录
		map.put("/tologin", "anon");
		map.put("/login", "anon");

		// 首页
		map.put("/web/index", "anon");

		// 无权限页面
		map.put("/unauthorized", "anon");

		/*-- 登出 --*/
		map.put("/logout", "logout");

		/*-- 需认证 --*/
		map.put("/**", "authc");

		return map;
	}
}
