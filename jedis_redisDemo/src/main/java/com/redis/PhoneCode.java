package com.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Scanner;

/**
 * @author wanyu
 * @createTime 2022-03-28 16:31
 */
public class PhoneCode {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入手机号：");
        String phone = scanner.next();
        verifyCode(phone);
        System.out.println("请输入" + phone + "接收到的验证码：");
        String code = scanner.next();
        getRedisCode("15340685459", code);
        scanner.close();
    }

    public static void getRedisCode(String phone, String code) {
        Jedis jedis = new Jedis("192.168.204.129", 6379);

        String codeKey = "verifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);

        boolean equals = redisCode.equals(code);
        if (equals) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        jedis.close();
    }


    public static void verifyCode(String phone) {
        Jedis jedis = new Jedis("192.168.204.129", 6379);

        //验证次数
        String countKey = "verifyCode" + phone + ":count";
        //验证码
        String codeKey = "verifyCode" + phone + ":code";

        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.getInteger(count) <= 2) {
            jedis.incr(countKey);
        } else {
            System.out.println("发送超过3次数");
            jedis.close();
            return;
        }

        String code = getCode();
        jedis.setex(codeKey, 120, code);
        jedis.close();
    }

    public static String getCode() {
        Random random = new Random();
        StringBuffer str = new StringBuffer(6);
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            str.append(i1);
        }
        System.out.println(str);
        return str.toString();
    }
}
