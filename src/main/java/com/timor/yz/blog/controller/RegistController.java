/**
 * timor-yz所有
 */
package com.timor.yz.blog.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timor.yz.blog.common.Constant;
import com.timor.yz.blog.common.JsonResult;
import com.timor.yz.blog.entity.User;
import com.timor.yz.blog.service.UserService;
import com.timor.yz.blog.utils.EmailUtils;
import com.timor.yz.blog.utils.MD5Utils;
import com.timor.yz.blog.utils.RandomStringUtils;
import com.timor.yz.blog.utils.StringUtils;

/**
 * @Description 用户注册处理控制器
 * @author YuanZhe
 * @date 2018年8月30日 下午4:24:19
 * 
 */
@Controller
// @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
// 1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return里的内容。
//  例如：本来应该到success.jsp页面的，则其显示success.
// 2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
// 3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解
@RequestMapping("/regist")
public class RegistController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(RegistController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @Description 用户注册
	 * @param model    Model对象
	 * @param email    用户邮箱地址
	 * @param password 密码
	 * @param phone    电话号码
	 * @param nickname 昵称
	 * @param code     验证码
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月10日 下午5:38:28
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(Model model, @RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "code", required = false) String code)
	{
		logger.info("用户注册 regist/regist, email : {}, password : {}, phone : {}, nickName : {}, code : {}", email,
				password, phone, nickName, code);

		// 用户信息有效性校验
		if (!valid(model, email, password, phone, nickName, code))
			return "../regist";

		// 注册
		User user = new User();
		user.setId(RandomStringUtils.getUUID());
		user.setEmail(email);
		user.setPhone(phone);
		user.setNickName(nickName);
		user.setPassword(MD5Utils.encodeToHex(Constant.SALT + password));// 密码进行“加盐”存储
		user.setImgUrl("/static/imgs/icon_m.jpg");
		userService.regist(user);
		logger.info("注册成功");

		// 生成邮件激活码，并保存到redis中
		String activateCode = MD5Utils.encodeToHex(Constant.SALT + email + password);
		redisTemplate.opsForValue().set(email, activateCode, 24, TimeUnit.HOURS);// redis保存激活码（24小时有效激活）

		// 发送激活邮件
		String[] toUsers = new String[] { email };
		String subject = "Timor网用户注册激活邮件通知";

		// 邮件内容
		String url = "http://localhost:8888";// 网站网址
		String content = "<div style='font-size:14px;line-height:25px;'>"//
				+ "欢迎注册成为<a href='" + url
				+ "' target='_blank' style='text-decoration:none;color:#19aaf8;'>Timor网</a>会员！<br />"//
				+ "您的注册账号为：" + email + "，<a href='" + url + "/regist/activateValid?email=" + email + "&activateCode="
				+ activateCode + "' target='_blank' style='text-decoration:none;color:#19aaf8;'>请于24小时内点击激活</a><br />"//
				+ "<span style='color:gray;'>该邮件为系统自动发送邮件，请勿回复！</span>"//
				+ "</div>";
		logger.info("发送激活邮件");
		EmailUtils.sendEmail(toUsers, null, subject, content, null);

		String message = email + "," + activateCode;
		model.addAttribute("message", message);

		return "/page/registSuccess";
	}

	/**
	 * @Description 注册数据后台校验
	 * @param model    Model对象
	 * @param email    用户邮箱地址
	 * @param password 密码
	 * @param phone    电话号码
	 * @param nickname 昵称
	 * @param code     验证码
	 * @return 是否合法（true|false）
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午4:41:38
	 */
	private boolean valid(Model model, String email, String password, String phone, String nickName, String code)
	{
		// 非空判断
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password) || StringUtils.isBlank(nickName)
				|| StringUtils.isBlank(code) || StringUtils.isBlank(code))
		{
			model.addAttribute("error", "请求参数缺失，请重新注册！");
			return false;
		}

		// 检测验证码有效性
		int b = checkValidateCode(code, 1);
		if (b == -1)
		{
			model.addAttribute("error", "验证码超时，请重新注册！");
			return false;
		} else if (b == 0)
		{
			model.addAttribute("error", "验证码不正确,请重新输入!");
			return false;
		}

		// 检测邮箱是否已存在
		User user = userService.getByEmail(email);
		if (user != null)
		{
			model.addAttribute("error", "该用户已经被注册！");
			return false;
		}

		return true;
	}

	/**
	 * @Description 检测激活码的有效性
	 * @param model Model对象
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:54:04
	 */
	@RequestMapping("/activateValid")
	public String activateValid(Model model)
	{
		logger.info("激活验证 regist/activateValid");
		HttpServletRequest request = getRequest();
		String email = request.getParameter("email");
		String activateCode = request.getParameter("activateCode");
		logger.info("Email : {}、Code : {}", email, activateCode);
		
		if (StringUtils.isBlank(email) || StringUtils.isBlank(activateCode))
		{
			model.addAttribute("fail", "您的激活码错误，请重新激活！");
			return "../page/regist";
		}

		String code = (String) redisTemplate.opsForValue().get(email);
		logger.info("Correct Code : {}", code);

		// 判断是否已激活
		User user = userService.getByEmail(email);
		if (user != null && user.getState() == 1)
		{
			model.addAttribute("success", "您已激活，请直接登录！");
			logger.info("用户“{}”激活成功", email);
			return "/login/toLogin";
		}

		// 判断激活码是否过期
		if (code == null)
		{
			model.addAttribute("fail", "您的激活码已过期，请重新注册！");
			logger.info("用户“{}”的激活码已过期，并将其用户数据删除", email);
			userService.deleteByEmail(email);
			return "index";
		}

		// 判断激活码正确性
		if (StringUtils.isBlank(activateCode) || !activateCode.equals(code))
		{
			model.addAttribute("fail", "您的激活码错误，请重新激活！");
			logger.info("用户“{}”的激活码错误", email);
			return "index";
		}

		// 用户激活
		User userUpd = new User();
		userUpd.setEmail(email);
		userUpd.setEnable(1);
		userUpd.setState(1);
		userService.updateByEmail(userUpd);

		model.addAttribute("email", email);
		return "/page/activeSuccess";
	}

	/**
	 * @Description 检测验证码是否正确
	 * @param model Model对象
	 * @param code  需检测的验证码
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午5:14:09
	 */
	@RequestMapping("/checkCode")
	@ResponseBody
	public ResponseEntity<JsonResult> checkCode(Model model,
			@RequestParam(value = "code", required = false) String code)
	{
		logger.info("注册-检测验证码“{}”是否正确 regist/checkCode", code);
		String vcode = (String) getSession().getAttribute(Constant.VERCODE_KEY_REGIST);

		boolean result = code.equalsIgnoreCase(vcode);
		logger.info("Code : {}, Correct Code : {}, result : {}", code, vcode, result);

		return ResponseEntity.ok(new JsonResult("200", result));
	}

	/**
	 * @Description 检测邮箱是否已经被注册
	 * @param model Model对象
	 * @param email 邮箱
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 上午11:24:00
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public ResponseEntity<JsonResult> checkEmail(Model model,
			@RequestParam(value = "email", required = false) String email)
	{
		logger.info("注册-检测邮箱“{}”是否已被注册 regist/checkEmail", email);
		String status = "200";
		Object result = null;
		try
		{
			User user = userService.getByEmail(email);
			result = user == null;
		} catch (Exception e)
		{
			status = "500";
			result = "系统繁忙，请稍后再试！";
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return ResponseEntity.ok(new JsonResult(status, result));
	}

	/**
	 * @Description 检测手机号是否已经被注册
	 * @param model Model对象
	 * @param phone 手机号
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月12日 上午11:24:00
	 */
	@RequestMapping("/checkPhone")
	@ResponseBody
	public ResponseEntity<JsonResult> checkPhone(Model model,
			@RequestParam(value = "phone", required = false) String phone)
	{
		logger.info("注册-检测手机号“{}”是否已被注册 regist/checkPhone", phone);
		String status = "200";
		Object result = null;
		try
		{
			User user = userService.getByPhone(phone);
			result = user == null;
		} catch (Exception e)
		{
			status = "500";
			result = "系统繁忙，请稍后再试！";
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return ResponseEntity.ok(new JsonResult(status, result));
	}
}
