/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.timor.yz.blog.common.ResultVO;
import com.timor.yz.blog.common.constant.Constant;
import com.timor.yz.blog.common.constant.Constant4ConfigFile;
import com.timor.yz.blog.common.validcode.CaptchaUtils;
import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.service.UserService;
import com.timor.yz.blog.utils.StringUtils;

/**
 * @Description 用户注册处理控制器
 * @author YuanZhe
 * @date 2018年12月26日 上午11:47:38
 * 
 */
@Controller
// @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
// 1)如果只是使用@RestController注解Controller， 则Controller中的方法无法返回jsp页面， 配置的视图解析器InternalResourceViewResolver不起作用， 返回的内容就是Return里的内容。
//  例如：本来应该到success.jsp页面的， 则其显示success.
// 2)如果需要返回到指定页面， 则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
// 3)如果需要返回JSON， XML或自定义mediaType内容到页面， 则需要在对应的方法上加上@ResponseBody注解
@RequestMapping("regist")
public class RegistController
{
	private static final Logger logger = LoggerFactory.getLogger(RegistController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @Description 跳转注册页面
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月25日 上午9:30:46
	 */
	@RequestMapping("toregist")
	public ModelAndView toregist()
	{
		logger.info("跳转注册页面...regist/toregist");
		if (SecurityUtils.getSubject().isAuthenticated())
		{
			logger.info("当前用户已登录， 前往首页！");
			return new ModelAndView("redirect:/web/index");// 已登录， 前往首页
		}
		return new ModelAndView("regist/regist");
	}

	/**
	 * @Description 用户注册
	 * @param user 用户注册信息
	 * @return 注册结果
	 * 
	 * @author YuanZhe
	 * @date 2018年12月26日 上午11:49:02
	 */
	@RequestMapping("regist")
	public ModelAndView regist(User user)
	{
		logger.info("用户注册...regist/regist， user：{}", user.toString());

		if (SecurityUtils.getSubject().isAuthenticated())
		{// 已登录， 前往首页
			logger.info("当前用户已登录， 前往首页！");
			return new ModelAndView("redirect:/web/index");
		}

		// 用户信息有效性校验
		ModelAndView mav = new ModelAndView();
		if (!this.valid(mav, user))
		{
			mav.setViewName("redirect:/regist/toRegistFail");
			return mav;
		}

		// 注册
		try
		{
			userService.regist(user);
			logger.info("注册成功！ user：{}", user.toString());
			mav.setViewName("redirect:/regist/toRegistSuccessful");
		} catch (Exception e)
		{
			logger.error("注册失败！", e);
			mav.addObject("msg", "注册失败：系统异常请重试或联系管理员：" + Constant4ConfigFile.admin_email);
			mav.setViewName("redirect:/regist/toRegistFail");
		}

		return mav;
	}

	/**
	 * @Description 注册数据后台校验
	 * @param mav  ModelAndView对象
	 * @param user 注册用户信息
	 * @return 验证结果（true|false）
	 * 
	 * @author YuanZhe
	 * @date 2018年12月26日 下午4:40:20
	 */
	private boolean valid(ModelAndView mav, User user)
	{
		// 非空判断
		if (user == null || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())
				|| StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getValidCode()))
		{
			mav.addObject("msg", "注册失败：请求参数缺失， 请重新注册！");
			return false;
		}

		// 检测验证码有效性
		int b = CaptchaUtils.checkValidateCode(user.getValidCode(), Constant.VERCODE_KEY_REGIST);
		if (b == -1 || b == 0)
		{
			mav.addObject("msg", "注册失败：验证码错误， 请重新输入！");
			return false;
		}

		// 检测用户名是否已存在
		User userExists = userService.getByUsername(user.getUsername());
		if (userExists != null)
		{
			mav.addObject("msg", "注册失败：该用户名已存在！");
			return false;
		}

		// 检测邮箱是否已被注册
		userExists = userService.getByEmail(user.getEmail());
		if (userExists != null)
		{
			mav.addObject("msg", "注册失败：该邮箱已被注册！");
			return false;
		}

		return true;
	}

	/**
	 * @Description 用户激活认证
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:54:04
	 */
	@RequestMapping("activateValid")
	public ModelAndView activateValid(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "activateCode", required = false) String activateCode)
	{
		logger.info("用户激活认证...regist/activateValid");
		logger.info("---> email：{}、 activateCode：{}", email, activateCode);

		if (StringUtils.isBlank(email) || StringUtils.isBlank(activateCode))
		{
			logger.info("激活所需参数缺失！");
			return new ModelAndView("redirect:/regist/toActivateFail", "msg", "激活码有误， 请重新激活！");
		}

		User user = userService.getByEmail(email);
		if (user == null)
		{
			logger.info("邮箱“{}”无对应用户信息！", email);
			return new ModelAndView("redirect:/regist/toActivateFail", "msg", "激活码已过期， 请重新注册！");
		}

		// 判断是否已激活
		if (user.getState() == 1)
		{
			logger.info("用户“{}”已激活成功， 无需重复激活！", user.getUsername());
			return new ModelAndView("redirect:/regist/toActivateSuccessful", "msg",
					"用户“" + user.getUsername() + "”已激活成功， 无需重复激活！");
		}

		// 判断激活码是否过期
		String code = (String) redisTemplate.opsForValue().get(email);
		logger.info("---> Correct Code：{}", code);
		if (StringUtils.isBlank(code))
		{
			userService.deleteByEmail(email);
			logger.info("激活码已过期， 并将其对应用户数据删除", email);
			return new ModelAndView("redirect:/regist/toActivateFail", "msg", "激活码已过期， 请重新注册！");
		}

		// 判断激活码正确性
		if (!activateCode.equals(code))
		{
			logger.info("激活码错误！链接中的激活码：{}、 正确的激活码：{}", activateCode, code);
			return new ModelAndView("redirect:/regist/toActivateFail", "msg", "激活码有误， 请重新激活！");
		}

		// 用户激活
		User userUpd = new User();
		userUpd.setEmail(email);
		userUpd.setState(1);
		userService.updateByEmail(userUpd);

		return new ModelAndView("redirect:/regist/toActivateSuccessful", "msg", "用户“" + user.getUsername() + "”激活成功！");
	}

	/**
	 * @Description 检测用户名是否已存在
	 * @param username 用户名
	 * @return 检测结果
	 * 
	 * @author YuanZhe
	 * @date 2018年12月27日 上午10:33:35
	 */
	@RequestMapping("checkUsername")
	@ResponseBody
	public ResultVO checkUsername(@RequestParam(value = "username", required = false) String username)
	{
		logger.info("注册-检测用户名“{}”是否已存在...regist/checkUsername", username);
		try
		{
			User user = userService.getByUsername(username);
			return user == null ? ResultVO.wrapSuccessfulResult() : ResultVO.wrapErrorResult("该用户名已存在！");
		} catch (Exception e)
		{
			logger.error("检测用户名失败！", e);
			return ResultVO.wrapErrorResult("系统异常， 请重试或联系管理员" + Constant4ConfigFile.admin_email + "！");
		}
	}

	/**
	 * @Description 检测邮箱是否已经被注册
	 * @param email 邮箱
	 * @return 检测结果
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 上午11:24:00
	 */
	@RequestMapping("checkEmail")
	@ResponseBody
	public ResultVO checkEmail(@RequestParam(value = "email", required = false) String email)
	{
		logger.info("注册-检测邮箱“{}”是否已被注册...regist/checkEmail", email);
		try
		{
			User user = userService.getByEmail(email);
			return user == null ? ResultVO.wrapSuccessfulResult() : ResultVO.wrapErrorResult("该邮箱已被注册！");
		} catch (Exception e)
		{
			logger.error("检测邮箱失败！", e);
			return ResultVO.wrapErrorResult("系统异常， 请重试或联系管理员" + Constant4ConfigFile.admin_email + "！");
		}
	}

	/**
	 * @Description 检测验证码有效性
	 * @param code 需检测的验证码
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:14:09
	 */
	@RequestMapping("checkCode")
	@ResponseBody
	public ResultVO checkCode(@RequestParam(value = "code", required = false) String code)
	{
		logger.info("注册-检测验证码“{}”是否正确...regist/checkCode", code);
		try
		{
			String vcode = CaptchaUtils.getValidCode(Constant.VERCODE_KEY_REGIST);
			boolean result = code.equalsIgnoreCase(vcode);
			logger.info("Code：{}， Correct Code：{}， result：{}", code, vcode, result);
			return result ? ResultVO.wrapSuccessfulResult() : ResultVO.wrapErrorResult("验证码输入有误");
		} catch (Exception e)
		{
			logger.error("验证码检测失败！", e);
			return ResultVO.wrapErrorResult("500", "系统异常， 请重试或联系管理员" + Constant4ConfigFile.admin_email + "！");
		}
	}

	/**
	 * @Description 跳转注册失败页面
	 * @param msg 失败消息
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午11:30:30
	 */
	@RequestMapping("toRegistFail")
	public ModelAndView toRegistFail(String msg)
	{
		logger.info("跳转注册失败页面...regist/toRegistFail");
		return new ModelAndView("regist/regist-fail", "msg", StringUtils.isNotBlank(msg) ? msg : "注册失败！请重新前往注册！");
	}

	/**
	 * @Description 跳转注册成功页面
	 * @param msg 成功消息
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午11:30:49
	 */
	@RequestMapping("toRegistSuccessful")
	public ModelAndView toRegistSuccessful(String msg)
	{
		logger.info("跳转注册成功页面...regist/toRegistSuccessful");
		return new ModelAndView("regist/regist-successful", "msg",
				StringUtils.isNotBlank(msg) ? msg : "注册成功！请及时前往激活！即将跳转至首页！");
	}

	/**
	 * @Description 跳转激活失败页面
	 * @param msg 失败消息
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午11:30:30
	 */
	@RequestMapping(value = "toActivateFail")
	public ModelAndView toActivateFail(String msg)
	{
		logger.info("跳转激活失败页面...regist/toActivateFail");
		return new ModelAndView("regist/activate-fail", "msg", StringUtils.isNotBlank(msg) ? msg : "激活失败！请重试！");
	}

	/**
	 * @Description 跳转激活成功页面
	 * @param msg 成功消息
	 * @return ModelAndView对象
	 * 
	 * @author YuanZhe
	 * @date 2018年12月28日 上午11:30:49
	 */
	@RequestMapping("toActivateSuccessful")
	public ModelAndView toActivateSuccessful(String msg)
	{
		logger.info("跳转激活成功页面...regist/toActivateSuccessful");
		return new ModelAndView("regist/activate-successful", "msg", StringUtils.isNotBlank(msg) ? msg : "激活成功！");
	}
}
