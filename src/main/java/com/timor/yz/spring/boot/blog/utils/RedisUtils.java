package com.timor.yz.spring.boot.blog.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

/**
 * @Description Redis工具类
 * @author YuanZhe
 * @date 2018年9月6日 上午11:38:17
 * 
 */
public class RedisUtils
{
	private RedisTemplate<String, Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate)
	{
		this.redisTemplate = redisTemplate;
	}

	// ============================== common ==============================
	/**
	 * @Description 指定缓存过期（生存）时间
	 * @param key  键
	 * @param time 过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @return 是否设置成功
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:38:42
	 */
	public boolean expire(String key, long time)
	{
		try
		{
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 获取指定key的过期时间（剩余生存时间）（单位：秒）
	 * @param key 键
	 * @return 过期时间（剩余生存时间）（单位：秒），返回0代表为永久有效
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:40:23
	 */
	public long getExpire(String key)
	{
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * @Description 判断key是否存在
	 * @param key 键
	 * @return true：存在、false：不存在
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:43:21
	 */
	public boolean hasKey(String key)
	{
		try
		{
			return redisTemplate.hasKey(key);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 清除缓存（根据 key 删除redis缓存数据）
	 * @param keys 键（1个或多个）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:44:01
	 */
	@SuppressWarnings("unchecked")
	public boolean del(String... keys)
	{
		try
		{
			if (keys != null && keys.length > 0)
			{
				if (keys.length == 1)
					redisTemplate.delete(keys[0]);
				else
					redisTemplate.delete(CollectionUtils.arrayToList(keys));
			}
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	// ============================== string ==============================
	/**
	 * @Description 获取 key 对应的值
	 * @param key 键
	 * @return 值
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:45:43
	 */
	public Object get(String key)
	{
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * @Description 设置键-值
	 * @param key   键
	 * @param value 值
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:47:40
	 */
	public boolean set(String key, Object value)
	{
		try
		{
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 设置键-值并设置其过期（生存）时间
	 * @param key   键
	 * @param value 值
	 * @param time  过期（生存）时间（如果time<=0，将设置无限期）（单位：秒）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:48:45
	 */
	public boolean set(String key, Object value, long time)
	{
		try
		{
			if (time > 0)
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			else
				set(key, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 增量/减量
	 * @param key   键
	 * @param delta 变化量
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:50:47
	 */
	public long incr(String key, long delta)
	{
		return redisTemplate.opsForValue().increment(key, delta);
	}

	// ============================== hash ==============================
	/**
	 * @Description 获取 key 中 hashKey 的值
	 * @param key     键
	 * @param hashKey 哈希键
	 * @return 值
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:56:44
	 */
	public Object hget(String key, String hashKey)
	{
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * @Description 获取存储在 key 上的全部哈希
	 * @param key 键
	 * @return 存储的全部哈希
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:58:09
	 */
	public Map<Object, Object> hmget(String key)
	{
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @Description 将 value 设置为 key 中 hashKey 的值
	 * @param key     键
	 * @param hashKey 哈希键
	 * @param value   值
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:58:42
	 */
	public boolean hset(String key, String hashKey, Object value)
	{
		try
		{
			redisTemplate.opsForHash().put(key, hashKey, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将 value 设置为 key 中 hashKey 的值，并设置过期（生存）时间
	 * @param key     键
	 * @param hashKey 哈希键
	 * @param value   值
	 * @param time    过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:07:04
	 */
	public boolean hset(String key, String hashKey, Object value, long time)
	{
		try
		{
			hset(key, hashKey, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将哈希（map）为添加为 key 的值
	 * @param key 键
	 * @param map 哈希（键值对）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:58:42
	 */
	public boolean hmset(String key, Map<String, Object> map)
	{
		try
		{
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将哈希（map）为添加为 key 的值，并设置过期（生存）时间
	 * @param key  键
	 * @param map  对应多个键值
	 * @param time 过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 上午11:59:15
	 */
	public boolean hmset(String key, Map<String, Object> map, long time)
	{
		try
		{
			hmset(key, map);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 删除 key 中的 hashKeys
	 * @param key      键
	 * @param hashKeys 哈希键
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:09:22
	 */
	public void hdel(String key, Object... hashKeys)
	{
		redisTemplate.opsForHash().delete(key, hashKeys);
	}

	/**
	 * @Description 判断 key 中的 hashKey 是否存在
	 * @param key     键
	 * @param hashKey 哈希键
	 * @return true：存在、false：不存在
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:10:30
	 */
	public boolean hHasKey(String key, String hashKey)
	{
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	/**
	 * @Description 增量/减量
	 * @param key     键
	 * @param hashKey 哈希键
	 * @param delta   变化量
	 * @return
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:12:31
	 */
	public double hincr(String key, String item, double delta)
	{
		return redisTemplate.opsForHash().increment(key, item, delta);
	}

	// ============================== list ==============================
	/**
	 * @Description 获取list缓存的内容（0 到 -1代表所有值）
	 * @param key   键
	 * @param start 开始
	 * @param end   结束
	 * @return 缓存内容
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:19:27
	 */
	public List<Object> lget(String key, long start, long end)
	{
		try
		{
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description 通过索引获取list中的值
	 * @param key   键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return 值
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:21:11
	 */
	public Object lget(String key, long index)
	{
		try
		{
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description 获取list缓存的长度
	 * @param key 键
	 * @return 长度
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:20:55
	 */
	public long lsize(String key)
	{
		try
		{
			return redisTemplate.opsForList().size(key);
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Description 将list放入缓存
	 * @param key   键
	 * @param value 值
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:22:20
	 */
	public boolean lset(String key, Object value)
	{
		try
		{
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将list放入缓存，并设置过期（生存）时间
	 * @param key   键
	 * @param value 值
	 * @param time  过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:22:59
	 */
	public boolean lset(String key, Object value, long time)
	{
		try
		{
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将list放入缓存
	 * @param key  键
	 * @param list list集合
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:23:55
	 */
	public boolean lset(String key, List<Object> list)
	{
		try
		{
			redisTemplate.opsForList().rightPushAll(key, list);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将list放入缓存，并设置过期（生存）时间
	 * @param key  键
	 * @param list list集合
	 * @param time 过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:25:01
	 */
	public boolean lset(String key, List<Object> value, long time)
	{
		try
		{
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 根据索引修改list中的某条数据
	 * @param key   键
	 * @param index 索引
	 * @param value 值
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:25:45
	 */
	public boolean lset(String key, long index, Object value)
	{
		try
		{
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 移除 count 个值为value 的缓存数据
	 * @param key   键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:26:39
	 */
	public long ldel(String key, long count, Object value)
	{
		try
		{
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	// ============================== set ==============================
	/**
	 * @Description 根据key获取Set中的所有值
	 * @param key 键
	 * @return 值
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:27:48
	 */
	public Set<Object> sget(String key)
	{
		try
		{
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description 根据value从一个set中查询,是否存在
	 * @param key   键
	 * @param value 值
	 * @return true：成功、false：失败
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:28:06
	 */
	public boolean sHasKey(String key, Object value)
	{
		try
		{
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description 将数据放入set缓存
	 * @param key    键
	 * @param values 值
	 * @return 成功个数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:28:29
	 */
	public long sset(String key, Object... values)
	{
		try
		{
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Description 将数据放入set缓存，并设置过期（生存）时间
	 * @param key    键
	 * @param time   过期（生存）时间；如果该值<=0，那么设置无效（单位：秒）
	 * @param values 值
	 * @return 成功个数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:29:05
	 */
	public long sset(String key, long time, Object... values)
	{
		try
		{
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0)
				expire(key, time);
			return count;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Description 获取set缓存的长度
	 * @param key 键
	 * @return 长度
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:30:04
	 */
	public long ssize(String key)
	{
		try
		{
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Description 移除值为value的数据
	 * @param key    键
	 * @param values 值
	 * @return 移除的个数
	 * 
	 * @author YuanZhe
	 * @date 2018年9月6日 下午3:30:21
	 */
	public long sdel(String key, Object... values)
	{
		try
		{
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	// ============================== zset ==============================

}
