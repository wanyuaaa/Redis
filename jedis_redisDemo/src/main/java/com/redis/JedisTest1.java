package com.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author wanyu
 * @createTime 2022-03-28 15:57
 */
public class JedisTest1 {

    @Test
    void test1() {
        //创建jedis
        Jedis jedis = new Jedis("192.168.204.129", 6379);

        jedis.set("name","lucy");

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    void test2() {

    }
}
