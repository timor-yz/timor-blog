/**
 * timor-yz所有
 */
package com.timor.yz.blog.exception;

/**
 * @Description 自定义异常
 * @author YuanZhe
 * @date 2018年12月28日 下午3:29:04
 * 
 */
public class MyException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;

	public MyException()
	{
		super();
	}

	public MyException(String msg)
	{
		super();
		this.msg = msg;
	}

	public MyException(String code, String msg)
	{
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
