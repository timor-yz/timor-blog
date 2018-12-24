/**
 * timor-yz所有
 */
package com.timor.yz.blog.common.enums;

/**
 * @Description 用户账号状态枚举
 * @author YuanZhe
 * @date 2018年12月21日 下午3:51:48
 * 
 */
public enum EnumUserState
{
	/**
	 * @Fields NORMAL : 正常
	 */
	NORMAL(1, "正常"),
	/**
	 * @Fields INACTIVE : 待激活
	 */
	INACTIVE(0, "待激活"),
	/**
	 * @Fields LOCK : 锁定
	 */
	LOCK(-1, "锁定");

	private Integer val;
	private String txt;

	private EnumUserState(Integer val, String txt)
	{
		this.val = val;
		this.txt = txt;
	}

	public Integer getVal()
	{
		return val;
	}

	public void setVal(Integer val)
	{
		this.val = val;
	}

	public String getTxt()
	{
		return txt;
	}

	public void setTxt(String txt)
	{
		this.txt = txt;
	}

}
