package com.redis;

import redis.clients.jedis.Jedis;

/**
 * @author wanyu
 * @createTime 2022-03-28 15:39
 */
public class JedisTest {
    public static void main(String[] args) {
        //创建jedis
        Jedis jedis = new Jedis("192.168.204.129", 6379);

        //测试
        String ping = jedis.ping();
        System.out.println(ping);
    }
}
