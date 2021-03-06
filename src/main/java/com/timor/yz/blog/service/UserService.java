/**
 * timor-yz所有
 */
package com.timor.yz.blog.service;

import java.util.List;

import com.timor.yz.blog.entity.User;

/**
 * @Description 用户信息业务处理接口
 * @author YuanZhe
 * @date 2018年8月30日 下午4:19:40
 * 
 */
public interface UserService
{

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
	 * @Description 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午3:42:47
	 */
	User getByUsername(String username);

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
	 * @Description 根据指定条件查询用户信息
	 * @param user 包含查询条件的用户信息对象
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:23:30
	 */
	List<User> findByParams(User user);

	/**
	 * @Description 用户注册，并发送激活邮件
	 * @param user 需注册的用户信息对象
	 * @throws Exception 抛出异常
	 * 
	 * @author YuanZhe
	 * @date 2018年12月26日 下午5:55:56
	 */
	void regist(User user) throws Exception;

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
