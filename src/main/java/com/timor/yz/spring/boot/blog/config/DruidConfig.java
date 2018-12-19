/**
 * timor-yz所有
 */
package com.timor.yz.spring.boot.blog.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Description
 *              <ul>
 *              Druid配置类
 *              <li>参考：https://blog.csdn.net/saytime/article/details/78963121</li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月3日 下午4:47:49
 * 
 */
@Configuration
// ignoreResourceNotFound=true表示找不到该文件,不报错
// properties文件默认使用ISO-8859-1,所有我们这里设置的是UTF-8
@PropertySource(value = "classpath:config/druid.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
public class DruidConfig
{
	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource()
	{
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	/**
	 * @Description 注册一个StatViewServlet
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月3日 下午4:23:54
	 */
	@Bean
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ServletRegistrationBean druidStatViewServlet()
	{
		// org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
		ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

		// 添加初始化参数：initParams
		// 白名单
		srb.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow)
		// 如果满足deny的话提示：“Sorry, you are not permitted to view this page”
		srb.addInitParameter("deny", "192.168.1.73");

		// 登录查看信息的账号密码.
		srb.addInitParameter("loginUsername", "admin");
		srb.addInitParameter("loginPassword", "123456");

		// 是否能够重置数据.
		srb.addInitParameter("resetEnable", "false");

		return srb;
	}

	/**
	 * @Description 注册一个FilterRegistrationBean
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月3日 下午4:24:02
	 */
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FilterRegistrationBean druidStatFilter()
	{

		FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());

		// 添加过滤规则.
		frb.addUrlPatterns("/*");

		// 添加需忽略的格式信息.
		frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

		return frb;
	}
}
