/**
 * timor-yz所有
 */
package com.timor.yz.blog.entity;

import java.util.Date;

import com.timor.yz.blog.utils.DateFormatUtils;

/**
 * @Description 用户信息实体类
 * @author YuanZhe
 * @date 2018年8月30日 下午4:09:14
 * 
 */
public class User extends BaseBean
{
	private static final long serialVersionUID = 1L;

	private String id;// id
	private String username;// 用户名
	private String password;// 密码
	private String email;// 邮箱
	private Integer state;// 状态（1：正常、0：未激活、-1：锁定）
	private String imgUrl;// 头像URL
	private String roleCode;// 所属角色Code role_code
	private Date createTime;// 创建（注册）时间

	// 临时变量
	private String validCode;// 验证码

	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("User { ");
		str.append("id=").append(id);
		str.append(", username=").append(username);
		str.append(", password=").append(password);
		str.append(", email=").append(email);
		str.append(", state=").append(state);
		str.append(", imgUrl=").append(imgUrl);
		str.append(", roleCode=").append(roleCode);
		str.append(", createTime=").append(DateFormatUtils.getDateDefStr(createTime));
		str.append(", validCode=").append(validCode);
		str.append(" }");
		return str.toString();
	}

	public String getValidCode()
	{
		return validCode;
	}

	public void setValidCode(String validCode)
	{
		this.validCode = validCode;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getState()
	{
		return state;
	}

	public void setState(Integer state)
	{
		this.state = state;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

}
