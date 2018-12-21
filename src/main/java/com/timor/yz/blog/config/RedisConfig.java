/**
 * timor-yz所有
 */
package com.timor.yz.blog.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description
 *              <ul>
 *              Redis配置类，继承org.springframework.cache.annotation.CachingConfigurerSupport
 *              <li>参考：https://blog.csdn.net/plei_yue/article/details/79362372</li>
 *              </ul>
 * @author YuanZhe
 * @date 2018年9月6日 上午10:51:20
 * 
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport
{
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	/**
	 * @Description
	 *              <ul>
	 *              设置key的自动生成规则
	 *              <li>配置spring-boot的注解，进行方法级别的缓存</li>
	 *              <li>使用：进行分割，可以很多显示出层级关系</li>
	 *              </ul>
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午4:39:17
	 */
	@Bean
	public KeyGenerator keyGenerator()
	{
		// 这里其实就是new了一个KeyGenerator对象，只是这是lambda表达式的写法，我感觉很好用，大家感兴趣可以去了解下
		return (target, method, params) ->
		{
			// 规则：本类名+方法名+参数名为key
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(":");
			sb.append(method.getName());
			for (Object obj : params)
			{
				sb.append(":" + String.valueOf(obj));
			}
			String rsToUse = String.valueOf(sb);
			logger.info("自动生成Redis Key -> [{}]", rsToUse);
			return rsToUse;
		};
	}

	/**
	 * @Description 初始化缓存管理器
	 * @author YuanZhe
	 * @date 2018年9月6日 下午4:39:17
	 */
	@Bean
	public CacheManager cacheManager()
	{
		// 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，我这里默认没有配置
		logger.info("Init -> [{}]", "CacheManager RedisCacheManager Start");
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(jedisConnectionFactory);
		return builder.build();
	}

	/**
	 * @Description 初始化RedisTemplate
	 * @author YuanZhe
	 * @date 2018年9月6日 下午4:39:17
	 */
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory)
	{
		// 设置序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisSerializer stringSerializer = new StringRedisSerializer();

		// 配置redisTemplate
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(factory);

		redisTemplate.setKeySerializer(stringSerializer); // key序列化
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
		redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash value序列化
		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	/**
	 * @Description
	 *              <ul>
	 *              异常处理
	 *              <li>当Redis发生异常时，打印日志，但是程序正常走</li>
	 *              </ul>
	 * @author YuanZhe
	 * @date 2018年9月6日 下午4:39:17
	 */
	@Bean
	public CacheErrorHandler errorHandler()
	{
		logger.info("Init -> [{}]", "Redis CacheErrorHandler");
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler()
		{
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key)
			{
				logger.error("Redis occur handleCacheGetError : key -> [{}]", key, e);
			}

			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value)
			{
				logger.error("Redis occur handleCachePutError : key -> [{}], value -> [{}]", key, value, e);
			}

			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)
			{
				logger.error("Redis occur handleCacheEvictError : key -> [{}]", key, e);
			}

			public void handleCacheClearError(RuntimeException e, Cache cache)
			{
				logger.error("Redis occur handleCacheClearError : ", e);
			}
		};
		return cacheErrorHandler;
	}

	/**
	 * @Description 此内部类就是把yml的配置数据，进行读取，创建JedisConnectionFactory和JedisPool，以供外部类初始化缓存管理器使用；参考：@ConfigurationProperties和@Value的作用
	 * @author YuanZhe
	 * @date 2018年9月6日 下午4:49:40
	 * 
	 */
	@ConfigurationProperties
	class DataJedisProperties
	{
		@Value("${spring.redis.host}")
		private String host;

		@Value("${spring.redis.password}")
		private String password;

		@Value("${spring.redis.port}")
		private int port;

		@Value("${spring.redis.timeout}")
		private int timeout;

		@Value("${spring.redis.jedis.pool.max-idle}")
		private int maxIdle;

		@Value("${spring.redis.jedis.pool.max-wait}")
		private long maxWaitMillis;

		@Bean
		@SuppressWarnings("deprecation")
		JedisConnectionFactory jedisConnectionFactory()
		{
			JedisConnectionFactory factory = new JedisConnectionFactory();
			factory.setHostName(host);
			factory.setPort(port);
			factory.setTimeout(timeout);
			factory.setPassword(password);

			logger.info("JedisConnectionFactory init successful");
			return factory;
		}

		@Bean
		public JedisPool redisPoolFactory()
		{
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(maxIdle);
			config.setMaxWaitMillis(maxWaitMillis);

			JedisPool jedisPool = new JedisPool(config, host, port, timeout, password);
			logger.info("JedisPool init successful, host -> [{}], port -> [{}]", host, port);
			return jedisPool;
		}
	}
}
