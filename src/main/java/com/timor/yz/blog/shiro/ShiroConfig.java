/**
 * timor-yz所有
 */
package com.timor.yz.blog.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.timor.yz.blog.shiro.matcher.MyCredentialsMatcher;
import com.timor.yz.blog.shiro.realm.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * @Description Shiro配置
 * @author YuanZhe
 * @date 2018年12月21日 下午3:29:02
 * 
 */
@Configuration
public class ShiroConfig
{
	public static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	/**
	 * @Description 用于thymeleaf模板使用shiro标签
	 * @return ShiroDialect对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月24日 上午9:49:41
	 */
	@Bean
	public ShiroDialect shiroDialect()
	{
		return new ShiroDialect();
	}

	/**
	 * @Description 配置Shiro过滤器
	 * @param securityManager 核心安全事务管理器DefaultWebSecurityManager对象
	 * @return ShiroFilterFactoryBean对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午5:01:33
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager)
	{
		logger.info("--------- 加载shiro ---------");
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

		// 配置核心安全事务管理器
		bean.setSecurityManager(securityManager);

		// 配置前往登录的url、登录成功后需跳转的url、登录后无权限需跳转的url
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/web/index");
		bean.setUnauthorizedUrl("/unauthorized");

		// 配置访问权限（anon：可匿名访问、authc：需认证（登录）后才可访问）
		bean.setFilterChainDefinitionMap(this.getFilterChainDefinitionMap());

		return bean;
	}

	/**
	 * @Description 配置核心安全事务管理器
	 * @param userRealm 自定义UserRealm对象
	 * @return 核心安全事务管理器DefaultWebSecurityManager对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午5:00:18
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager setSecurityManager(@Qualifier("userRealm") UserRealm userRealm)
	{
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	/**
	 * @Description 配置Realm
	 * @param matcher 凭证匹配器HashedCredentialsMatcher对象
	 * @return 自定义UserRealm对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午4:58:54
	 */
	@Bean(name = "userRealm")
	public UserRealm userRealm(@Qualifier("myCredentialsMatcher") MyCredentialsMatcher matcher)
	{
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(matcher);// 设置凭证匹配器
		return userRealm;
	}

	/**
	 * @Description 配置自定义凭证（密码）比较器
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年12月24日 上午10:14:13
	 */
	@Bean(name = "myCredentialsMatcher")
	public MyCredentialsMatcher credentialsMatcher()
	{
		return new MyCredentialsMatcher();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	{
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
	{
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager securityManager)
	{
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * @Description 获取访问权限配置（anon：可匿名访问、authc：需认证（登录）后才可访问）
	 * @return 访问权限配置Map
	 * 
	 * @author YuanZhe
	 * @date 2018年12月24日 下午3:04:08
	 */
	private Map<String, String> getFilterChainDefinitionMap()
	{
		Map<String, String> map = new LinkedHashMap<>();

		// 静态资源
		map.put("/js/**", "anon");
		map.put("/css/**", "anon");
		map.put("/imgs/**", "anon");
		map.put("/layui/**", "anon");
		map.put("/common/**", "anon");

		// 注册/登录相关
		map.put("/regist", "anon");
		map.put("/tologin", "anon");
		map.put("/login", "anon");

		// 首页
		map.put("/web/index", "anon");
		
		// 无权限页面
		map.put("/unauthorized", "anon");

		// 需认证
		map.put("/**", "authc");

		return map;
	}
}
