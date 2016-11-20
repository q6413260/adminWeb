package com.xiaoming.util;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xiaoming on 17/11/2016.
 */
public class JedisClientManager {
    private final static JedisPool pool = ApplicationContextUtil.getBean("jedisPool");

    public void putObject(String key, Object object) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = new Gson().toJson(object);
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public <T> T getObject(String key, Class<T> object) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            String value = jedis.get(key);
            if (StringUtils.isEmpty(value)) {
                return null;
            }
            return new Gson().fromJson(value, object);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
