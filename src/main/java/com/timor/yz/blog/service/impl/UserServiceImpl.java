/**
 * timor-yz所有
 */
package com.timor.yz.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.mapper.UserMapper;
import com.timor.yz.blog.service.UserService;

/**
 * @Description 用户信息业务处理实现类（JDBC）
 * @author YuanZhe
 * @date 2018年8月30日 下午4:21:10
 * 
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;

	public User getByUsername(User user)
	{
		return userMapper.getByUsername(user);
	}

	@Transactional
	public int regist(User user)
	{
		return userMapper.insert(user);
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

	public List<User> queryAll()
	{
		return userMapper.queryAll();
	}

}
