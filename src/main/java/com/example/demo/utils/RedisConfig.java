package com.example.demo.utils;

import com.example.demo.entity.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisConfig {
    private  Logger log = LoggerFactory.getLogger(WX_TokenUtil.class);
    @Autowired
    private  RedisTemplate redisTemplate;

    public  AccessToken getAccessToken(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        if( operations.get("token") != null && !("".equals(operations.get("token")))){
            AccessToken access_token = new AccessToken();
            access_token.setAccessToken(operations.get("token"));
            access_token.setExpiresin(7200);
            log.info("token存在缓存已使用缓存："+operations.get("token"));
            return access_token;
        }
        return null;
    }

    public  void setAccessToken(String accessToken,long time){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("token",accessToken);
        operations.set("token",accessToken,time, TimeUnit.SECONDS);
        log.info("token没有缓存，新生成的："+accessToken);
    }
}
