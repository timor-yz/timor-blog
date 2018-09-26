package com.timor.service;

import java.util.List;

import com.timor.entity.User;

/**
 * @Description 用户信息业务处理接口
 * @author YuanZhe
 * @date 2018年8月30日 下午4:19:40
 * 
 */
public interface UserService
{
	/**
	 * @Description 用户注册
	 * @param user 需注册的用户信息对象
	 * @return 1：成功、0：失败、-1：邮箱已存在、-2：手机号已存在、-3：昵称已存在
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:19:50
	 */
	int regist(User user);

	/**
	 * @Description 用户登录
	 * @param user 需登录的用户信息对象
	 * @return 登录用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:20:33
	 */
	User login(User user);

	/**
	 * @Description 根据id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:22:21
	 */
	User getById(String id);

	/**
	 * @Description 根据email获取用户信息
	 * @param email email
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:22:21
	 */
	User getByEmail(String email);

	/**
	 * @Description 根据手机号获取用户信息
	 * @param phone 手机号
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:22:21
	 */
	User getByPhone(String phone);

	/**
	 * @Description 根据指定条件查询用户信息
	 * @param user 包含查询条件的用户信息对象
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:23:30
	 */
	List<User> findByParams(User user);

	/**
	 * @Description 修改用户信息
	 * @param user 需进行修改的用户信息对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:24:37
	 */
	void update(User user);

	/**
	 * @Description 根据邮箱修改用户指定信息
	 * @param user 需进行修改的用户信息对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 上午11:04:19
	 */
	void updateByEmail(User user);

	/**
	 * @Description 根据邮箱删除用户信息
	 * @param email 邮箱
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午5:51:24
	 */
	void deleteByEmail(String email);
}
