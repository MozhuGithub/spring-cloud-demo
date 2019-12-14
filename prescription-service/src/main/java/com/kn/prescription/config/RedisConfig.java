package com.kn.prescription.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * @ClassName: RedisConfig
 * @Description TODO:
 * @Date: 2019/12/13 9:29
 * @Author: Kn
 */
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings({ "rawtypes" })
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
