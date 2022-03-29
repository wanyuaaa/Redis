package com.redis.springboot_redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanyu
 * @createTime 2022-03-29 9:37
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis() {
        //设置值到redis
        //redisTemplate.watch("name");
        //redisTemplate.multi();
        redisTemplate.opsForValue().set("name", "lucy");
        //redisTemplate.exec();
        //redisTemplate.unwatch();
        //从redis获取值
        String name = (String) redisTemplate.opsForValue().get("name");
        return name;
    }

}
