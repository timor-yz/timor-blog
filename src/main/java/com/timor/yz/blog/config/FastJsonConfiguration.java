/**
 * timor-yz所有
 */
package com.timor.yz.blog.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 *              <ul>
 *              FastJson配置类（SpringBoot默认JSON转换器为JackJson）
 *              <li>参考：https://blog.csdn.net/saytime/article/details/78963155</li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月3日 下午5:13:58
 * 
 */
@Configuration
@SuppressWarnings("deprecation")
public class FastJsonConfiguration extends WebMvcConfigurerAdapter
{
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}
}
