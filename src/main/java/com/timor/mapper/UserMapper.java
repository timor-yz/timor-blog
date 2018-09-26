package com.timor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.timor.entity.User;

/**
 * @Description 用户信息持久化接口
 * @author YuanZhe
 * @date 2018年9月7日 下午3:25:14
 * 
 */
@Mapper
public interface UserMapper
{

	/**
	 * @Description 根据指定条件查询用户信息
	 * @param user 包含查询条件的用户信息对象
	 * @return 用户信息
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:23:30
	 */
	List<User> get(User user);

	/**
	 * @Description 用户新增
	 * @param user 需新增的用户信息对象
	 * @return 成功数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:19:50
	 */
	int add(User user);

	/**
	 * @Description 根据id修改用户信息
	 * @param user 需进行修改的用户信息对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:24:37
	 */
	void update(User user);

	/**
	 * @Description 根据邮箱修改用户信息
	 * @param user 需进行修改的用户信息对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午2:24:37
	 */
	void updateByEmail(User user);

	/**
	 * @Description 根据email删除用户信息
	 * @param email email
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午5:51:24
	 */
	void deleteByEmail(String email);
}