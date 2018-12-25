/**
 * timor-yz所有
 */
package com.timor.yz.blog.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.timor.yz.blog.common.enums.EnumUserState;
import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.service.UserService;

/**
 * @Description 用户Realm
 * @author YuanZhe
 * @date 2018年12月21日 下午3:37:38
 * 
 */
public class UserRealm extends AuthorizingRealm
{
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	/**
	 * @Description 授权
	 * @param principals PrincipalCollection对象
	 * @return 授权信息
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午3:37:38
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// TODO Timor 目前采用硬编码方式，后面需查询数据库赋值
		// 获取用户角色
		List<String> roles = new ArrayList<String>();
		roles.add("normal");
		info.addRoles(roles);

		// 获取用户具体权限
		List<String> permissions = new ArrayList<String>();
		permissions.add("user:query");
		permissions.add("user:view");
		info.addStringPermissions(permissions);

		return info;
	}

	/**
	 * @Description 认证
	 * @param token AuthenticationToken对象
	 * @return 认证信息
	 * @throws AuthenticationException
	 * 
	 * @author YuanZhe
	 * @date 2018年12月21日 下午3:37:38
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String username = upToken.getUsername();

		logger.info("------> 登录用户名 : {}", username);

		User user = userService.getByUsername(upToken.getUsername());

		if (user == null)
			throw new UnknownAccountException("账号或密码错误!");// 没找到帐号

		if (EnumUserState.LOCK.getVal() == user.getState())
			throw new LockedAccountException("该账号已被锁定! 如需帮助, 请联系管理员:yuanzhe13579@163.com");// 帐号被锁定

		return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getId()),
				this.getName());
	}

}
