package com.kj_redis_application.config;

import com.kj_redis_application.binding.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnection() {

        JedisConnectionFactory jedis = new JedisConnectionFactory();

        //Redis Server properties(If redis server have been register in different system we have to set it.)


        return jedis;
    }

    @Bean
    public RedisTemplate<String, Country> redisTemplate() {
        RedisTemplate<String, Country> rt = new RedisTemplate<>();

        rt.setConnectionFactory(jedisConnection());

        return rt;
    }

}
