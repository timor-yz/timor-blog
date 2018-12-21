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
	private String email;// 邮箱
	private String phone;// 手机号
	private String nickName;// 昵称
	private String password;// 密码
	private Integer state;// 状态（1：正常、0：未激活、-1：禁用）
	private Integer enable;// 是否启用（1：是、0：否）
	private String imgUrl;// 头像URL
	private Date createTime;// 创建（注册）时间

	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("User { ");
		str.append("id=").append(id);
		str.append(", email=").append(email);
		str.append(", phone=").append(phone);
		str.append(", nickName=").append(nickName);
		str.append(", password=").append(password);
		str.append(", state=").append(state);
		str.append(", enable=").append(enable);
		str.append(", imgUrl=").append(imgUrl);
		str.append(", createTime=").append(DateFormatUtils.getDateDefStr(createTime));
		str.append(" }");
		return str.toString();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Integer getState()
	{
		return state;
	}

	public void setState(Integer state)
	{
		this.state = state;
	}

	public Integer getEnable()
	{
		return enable;
	}

	public void setEnable(Integer enable)
	{
		this.enable = enable;
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
