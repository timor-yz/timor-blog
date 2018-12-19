/**
 * timor-yz所有
 */
package com.timor.yz.spring.boot.blog.common;

/**
 * @Description 通用json返回类
 * @author YuanZhe
 * @date 2018年8月30日 下午4:23:34
 * 
 */
public class JsonResult
{
	private String status = null;// 返回状态码

	private Object result = null;// 返回结果

	public JsonResult()
	{
		super();
	}

	public JsonResult(String status, Object result)
	{
		super();
		this.status = status;
		this.result = result;
	}

	public JsonResult status(String status)
	{
		this.status = status;
		return this;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}

}
