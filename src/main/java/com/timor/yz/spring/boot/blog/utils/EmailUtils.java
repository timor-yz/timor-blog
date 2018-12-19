/**
 * timor-yz所有
 */
package com.timor.yz.spring.boot.blog.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description 邮件发送工具类
 * @author YuanZhe
 * @date 2018年9月3日 下午5:33:17
 * 
 */
@Component
public class EmailUtils
{
	private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

	@Autowired
	private Environment env;

	/**
	 * @Fields auth : 认证机制开关，记得开启
	 */
	private static String auth;

	/**
	 * @Fields host : 邮箱服务器主机
	 */
	private static String host;

	/**
	 * @Fields port : 端口号（不加密：25，加密：465、587）
	 */
	private static int port;

	/**
	 * @Fields protocol : 协议（例：smtp、pop3、imap）
	 */
	private static String protocol;

	/**
	 * @Fields username : 发件方账户
	 */
	private static String username;

	/**
	 * @Fields password : 密码/授权码。qq邮箱的话此处设置16位授权码，不是邮箱密码，切记！
	 */
	private static String password;

	/**
	 * @Fields isSSL : 是否发送加密邮件（当该值发生变化后，port的值也需随之改变）
	 */
	private static boolean isSSL;

	/**
	 * @Fields defaultEncoding : 默认邮件编码（例：UTF-8）
	 */
	private static String defaultEncoding;

	/**
	 * @Fields timeout : 连接超时时间，单位毫秒
	 */
	private static String timeout;

	@PostConstruct
	public void initParam()
	{
		logger.info("-------------------- Init the params for send mail. Start --------------------");
		auth = env.getProperty("spring.mail.smtp.auth");
		host = env.getProperty("spring.mail.host");
		protocol = env.getProperty("spring.mail.transport.protocol");
		port = env.getProperty("spring.mail.smtp.port", Integer.class);
		username = env.getProperty("spring.mail.auth.name");
		password = env.getProperty("spring.mail.auth.password");
		defaultEncoding = env.getProperty("spring.mail.default-encoding");
		isSSL = env.getProperty("spring.mail.is.ssl", Boolean.class);
		timeout = env.getProperty("spring.mail.smtp.timeout");
		logger.info(
				"Send Email Params : { auth : {}, host : {}, protocol : {}, port : {}, username : {}, password : {}, defaultEncoding : {}, isSSL : {}, timeout : {} }",
				auth, host, protocol, port, username, password, defaultEncoding, isSSL, timeout);
		logger.info("-------------------- Init the params for send mail. End --------------------");
	}

	/**
	 * @Description 发送邮件
	 * @param toUsers     收件人邮箱地址
	 * @param ccUsers     抄送人邮箱地址
	 * @param subject     主题/标题
	 * @param content     邮件内容
	 * @param attachfiles 附件列表（key : name[文件名称] && url[文件路径字符串]）
	 * @return 是否发送成功
	 * 
	 * @author YuanZhe
	 * @date 2018年9月3日 下午5:15:14
	 */
	public static boolean sendEmail(String[] toUsers, String[] ccUsers, String subject, String content,
			List<Map<String, String>> attachfiles)
	{
		logger.info("---------- Send Email. Start ----------");
		logger.info(
				"Send Email Params : { auth : {}, host : {}, protocol : {}, port : {}, username : {}, password : {}, defaultEncoding : {}, isSSL : {}, timeout : {} }",
				auth, host, protocol, port, username, password, defaultEncoding, isSSL, timeout);
		logger.info("Request Params : { toUsers : {}, ccUsers : {}, subject : {}, content : {}, attachfiles : {} }",
				StringUtils.join(toUsers, "、"), StringUtils.join(ccUsers, "、"), subject, content,
				StringUtils.join(attachfiles));

		boolean flag = false;
		try
		{
			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
			javaMailSender.setHost(host);
			javaMailSender.setPort(port);
			javaMailSender.setUsername(username);
			javaMailSender.setPassword(password);
			javaMailSender.setProtocol(protocol);
			javaMailSender.setDefaultEncoding(defaultEncoding);

			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", auth);
			props.setProperty("mail.smtp.timeout", timeout);
			if (isSSL)
			{// 加密邮件
				logger.info("----- Encrypted Email. Start -----");
				MailSSLSocketFactory sf = null;
				try
				{
					sf = new MailSSLSocketFactory();
					sf.setTrustAllHosts(true);
					props.put("mail.smtp.ssl.enable", "true");
					props.put("mail.smtp.ssl.socketFactory", sf);
					logger.info("Encrypted Email Success!");
				} catch (GeneralSecurityException e)
				{
					logger.error("Encrypted Email Fail!", e);
				}
				logger.info("----- Encrypted Email. End -----");
			}
			javaMailSender.setJavaMailProperties(props);

			MimeMessage mailMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mailMsg, true);
			msgHelper.setFrom(username);// 发件人
			msgHelper.setTo(toUsers);// 接收人
			if (ccUsers != null && ccUsers.length > 0)
			{// 抄送人
				msgHelper.setCc(ccUsers);
			}
			msgHelper.setSubject(subject);// 邮件主题
			msgHelper.setText(content, true);// 邮件内容（true：html格式邮件）
			if (attachfiles != null && attachfiles.size() > 0)
			{// 附件
				for (Map<String, String> attachfile : attachfiles)
				{
					String attachfileName = attachfile.get("name");
					File file = new File(attachfile.get("url"));
					msgHelper.addAttachment(attachfileName, file);
				}
			}

			// 发送邮件
			javaMailSender.send(mailMsg);
			flag = true;
			logger.info("Send Email Success!");
		} catch (Exception e)
		{
			logger.error("Send Email Fail!", e);
		}
		logger.info("---------- Send Email. End ----------");
		return flag;
	}

}
