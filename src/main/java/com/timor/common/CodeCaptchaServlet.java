package com.timor.common;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description 验证码生成Utils
 * @author YuanZhe
 * @date 2018年9月10日 下午4:42:50
 * 
 */
@WebServlet(urlPatterns = "/captchaServlet") // Servlet设置参考：https://blog.csdn.net/qq_27905183/article/details/79075921
public class CodeCaptchaServlet extends HttpServlet
{
	private static final long serialVersionUID = 5413310437308046316L;

	public void init() throws ServletException
	{
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		// 删除request中key对应的值
		session.removeAttribute(Constant.VERCODE_KEY_REGIST);

		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成验证码图像对象
		SCaptcha sCaptcha = new SCaptcha(100, 38);
		BufferedImage image = sCaptcha.create();

		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());

		// 将生成的随机字符串写入request
		session.setAttribute(Constant.VERCODE_KEY_REGIST, sCaptcha.getCode());
	}

}
