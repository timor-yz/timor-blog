/**
 * timor-yz所有
 */
package com.timor.yz.blog.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.timor.yz.blog.utils.RandomUtils;

/**
 * @Description 验证码生成器
 * @author YuanZhe
 * @date 2018年9月11日 下午3:37:07
 * 
 */
public class SCaptcha
{
	// 图片宽度
	private int width = 100;

	// 图片高度
	private int height = 40;

	// 验证码字符个数
	private int codeCount = 4;

	// 验证码干扰线条数
	private int lineCount = 10;

	// 验证码
	private String code = null;

	private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	public SCaptcha()
	{
		super();
	}

	/**
	 * @param width  图片宽度
	 * @param height 图片高度
	 */
	public SCaptcha(int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
	}

	/**
	 * @param width     图片宽度
	 * @param height    图片高度
	 * @param codeCount 验证码个数
	 */
	public SCaptcha(int width, int height, int codeCount)
	{
		super();
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
	}

	/**
	 * @param width     图片宽度
	 * @param height    图片高度
	 * @param codeCount 验证码个数
	 * @param dotCount  干扰线条数
	 */
	public SCaptcha(int width, int height, int codeCount, int lineCount)
	{
		super();
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
	}

	/**
	 * @Description 生成验证码图片
	 * @return 验证码图片对象
	 * 
	 * @author YuanZhe
	 * @date 2018年9月11日 下午4:11:49
	 */
	public BufferedImage create()
	{
		int x = width / (codeCount + 1);

		int codeY = height - 4;

		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 将图像填充为白色
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		// 设置字体，字体的大小应该根据图片的高度来定
		g.setFont(new Font("Fixedsys", Font.PLAIN, height - 2));

		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(Color.GRAY);
		for (int i = 0; i < lineCount; i++)
		{
			int x2 = RandomUtils.nextInt(0, width);
			int y2 = RandomUtils.nextInt(0, height);
			int xl = RandomUtils.nextInt(0, width);
			int yl = RandomUtils.nextInt(0, height);
			g.drawLine(x2, y2, x2 + xl, y2 + yl);
		}

		// 随机产生codeCount位的验证码
		StringBuffer randomCode = new StringBuffer();// randomCode用于保存随机产生的验证码
		int red = 0, green = 0, blue = 0;
		for (int i = 0; i < codeCount; i++)
		{
			// 得到随机产生的验证码数字
			String strRand = String.valueOf(codeSequence[RandomUtils.nextInt(0, codeSequence.length)]);

			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同
			red = RandomUtils.nextInt(0, 255);
			green = RandomUtils.nextInt(0, 255);
			blue = RandomUtils.nextInt(0, 255);

			// 用随机产生的颜色将验证码绘制到图像中
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, i * x + (x / 2), codeY);

			// 将产生的四个随机数组合在一起
			randomCode.append(strRand);
		}
		this.code = randomCode.toString();// 获取生成的验证码

		// 生成图象
		g.dispose();

		return buffImg;

	}

	public String getCode()
	{
		return code;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setCodeCount(int codeCount)
	{
		this.codeCount = codeCount;
	}

	public void setLineCount(int lineCount)
	{
		this.lineCount = lineCount;
	}

	public void setCodeSequence(char[] codeSequence)
	{
		this.codeSequence = codeSequence;
	}

}