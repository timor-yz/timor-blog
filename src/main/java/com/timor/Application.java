package com.timor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description SpringBoot项目启动类
 * @author YuanZhe
 * @date 2018年9月12日 下午4:35:00
 * 
 */
@SpringBootApplication
@EnableTransactionManagement // 启用事务管理注解
@ServletComponentScan // 用于Servlet，参考：https://blog.csdn.net/qq_27905183/article/details/79075921
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(Application.class);
	}
}