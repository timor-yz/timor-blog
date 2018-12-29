/**
 * timor-yz所有
 */
package com.timor.yz.blog.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timor.yz.blog.common.constant.Constant4ConfigFile;
import com.timor.yz.blog.common.enums.EnumUserState;
import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.mapper.UserMapper;
import com.timor.yz.blog.service.UserService;
import com.timor.yz.blog.shiro.Encryption;
import com.timor.yz.blog.utils.EmailUtils;
import com.timor.yz.blog.utils.MD5Utils;
import com.timor.yz.blog.utils.RandomStringUtils;

/**
 * @Description 用户信息业务处理实现类
 * @author YuanZhe
 * @date 2018年8月30日 下午4:21:10
 * 
 */
@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public User getByUsername(String username)
	{
		User user = new User();
		user.setUsername(username);
		List<User> users = userMapper.getByParams(user);
		return users != null && users.size() == 1 ? users.get(0) : null;
	}

	@Transactional(rollbackFor = { Exception.class })
	public void regist(User user) throws Exception
	{
		/* 用户注册 */
		user.setId(RandomStringUtils.getUUID());
		user.setPassword(Encryption.pwdEncrypt(user.getPassword(), user.getUsername()));// 密码进行“加盐”存储
		user.setState(EnumUserState.INACTIVE.getVal());
		user.setImgUrl(Constant4ConfigFile.user_default_avatar_img_url);
		user.setRoleCode(Constant4ConfigFile.user_default_role_code);
		userMapper.insert(user);

		/* 发送激活邮件 */
		String activateCode = MD5Utils.encodeToHex(user.getUsername() + user.getEmail());// 生成邮件激活码
		// TODO 需编写定时任务：删除超时未激活的用户
		redisTemplate.opsForValue().set(user.getEmail(), activateCode, 24, TimeUnit.HOURS);// redis保存激活码（24小时有效激活）

		// 邮件内容
		String email = user.getEmail();
		String url = Constant4ConfigFile.project_url_prefix;
		// TODO 配置模板文件
		String content = "<div style='font-size:14px;line-height:25px;'>"//
				+ "欢迎注册成为<a href='" + url
				+ "' target='_blank' style='text-decoration:none;color:#19aaf8;'>timor-yz博客</a>用户！"//
				+ "<br />您的账号为：" + user.getUsername() + "，请于注册后24小时内激活！<a href='" + url + "/regist/activateValid?email="
				+ email + "&activateCode=" + activateCode
				+ "' target='_blank' style='text-decoration:none;color:#19aaf8;'>&nbsp;&nbsp;前往激活&gt&gt</a>"//
				+ "<br /><span style='color:gray;'>该邮件为系统自动发送邮件，请勿回复！</span>" + "</div>";
		logger.info("发送激活邮件! 收件人 : {}、 邮件内容 : {}", email, content);
		boolean result = EmailUtils.sendEmail(email, "timor-yz博客用户注册激活邮件通知", content);// 发送邮件
		if (!result)
			throw new RuntimeException("激活邮件发送失败!");
	}

	public User login(User user)
	{
		List<User> list = userMapper.getByParams(user);
		return list != null && list.size() == 1 ? list.get(0) : null;
	}

	public User getById(String id)
	{
		User user = new User();
		user.setId(id);
		List<User> list = userMapper.getByParams(user);
		return list != null && list.size() == 1 ? list.get(0) : null;
	}

	public User getByEmail(String email)
	{
		User user = new User();
		user.setEmail(email);
		List<User> list = userMapper.getByParams(user);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public List<User> findByParams(User user)
	{
		return userMapper.getByParams(user);
	}

	@Transactional
	public void update(User user)
	{
		userMapper.update(user);
	}

	@Transactional
	public void updateByEmail(User user)
	{
		userMapper.updateByEmail(user);
	}

	@Transactional
	public void deleteByEmail(String email)
	{
		userMapper.deleteByEmail(email);
	}

}
