package com.xiao.login.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置文件
 *
 * @author Administrator
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    /** redis服务器地址 */
    @Value("${spring.redis.host}")
    private String host;

    /** redis端口号*/
    @Value("${spring.redis.port}")
    private int port;

    /** 连接超时时间*/
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool redisPoolFactory() {
        log.info("Jedis注入");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return  jedisPool;
    }


}
