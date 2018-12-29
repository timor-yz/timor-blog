/**
 * timor-yz所有
 */
package com.timor.yz.blog.common;

/**
 * @Description 通用json返回类
 * @author YuanZhe
 * @date 2018年8月30日 下午4:23:34
 * 
 */
public class ResultVO
{
	private boolean success;// 是否成功

	private String errCode;// 错误码

	private String msg;// 提示信息

	private Object data;// 结果

	private Integer page = 0;// 当前页码

	private Long count = 0L;// 数据总数

	private Integer limit = 10;// 每页显示个数

	private ResultVO()
	{
	}

	public static ResultVO wrapSuccessfulResult()
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data, String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data, Long count)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		vo.setCount(count);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data, Long count, String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		vo.setCount(count);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data, Long count, Integer limit)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		vo.setCount(count);
		vo.setLimit(limit);
		return vo;
	}

	public static ResultVO wrapSuccessfulResult(Object data, Long count, Integer limit, Integer page, String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(true);
		vo.setData(data);
		vo.setCount(count);
		vo.setPage(page);
		vo.setLimit(limit);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO wrapErrorResult()
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(false);
		return vo;
	}

	public static ResultVO wrapErrorResult(String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(false);
		vo.setMsg(msg);
		return vo;
	}

	public static ResultVO wrapErrorResult(String code, String msg)
	{
		ResultVO vo = new ResultVO();
		vo.setSuccess(false);
		vo.setErrCode(code);
		vo.setMsg(msg);
		return vo;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getErrCode()
	{
		return errCode;
	}

	public void setErrCode(String errCode)
	{
		this.errCode = errCode;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Long getCount()
	{
		return count;
	}

	public void setCount(Long count)
	{
		this.count = count;
	}

	public Integer getLimit()
	{
		return limit;
	}

	public void setLimit(Integer limit)
	{
		this.limit = limit;
	}

}
