/**
 * timor-yz所有
 */
package com.timor.yz.blog.common.constant;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description 配置文件常量类
 * @author YuanZhe
 * @date 2018年12月26日 下午4:50:09
 * 
 */
@Component
public class Constant4ConfigFile
{
	private static final Logger logger = LoggerFactory.getLogger(Constant4ConfigFile.class);

	/**
	 * @Fields PROJECT_URL : 项目网址前缀
	 */
	public static String project_url_prefix;

	/**
	 * @Fields admin_email : 管理员联系邮箱地址
	 */
	public static String admin_email;

	/**
	 * @Fields user_default_avatar_img_url : 用户默认头像URL
	 */
	public static String user_default_avatar_img_url;

	/**
	 * @Fields user_default_role_code : 用户默认角色Code
	 */
	public static String user_default_role_code;

	@Autowired
	private Environment env;

	/**
	 * @Description 初始化配置文件常量
	 * 
	 * @author YuanZhe
	 * @date 2018年12月26日 下午5:04:57
	 */
	@PostConstruct
	public void init()
	{
		logger.info("-------------------- Init the params for config file. Start --------------------");
		try
		{
			project_url_prefix = env.getProperty("constant.project_url_prefix");
			admin_email = env.getProperty("constant.admin_email");
			user_default_avatar_img_url = env.getProperty("constant.user_default.avatar_img_url");
			user_default_role_code = env.getProperty("constant.user_default.role_code");

			logger.error("---> Successful!");
		} catch (Exception e)
		{
			logger.error("---> Failed to init the params for config file.", e);
		}
		logger.info("-------------------- Init the params for config file. End --------------------");
	}
}
