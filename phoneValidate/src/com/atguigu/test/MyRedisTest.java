package com.atguigu.test;

import redis.clients.jedis.Jedis;

public class MyRedisTest {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("192.168.153.128",6379);
        System.out.println(jedis.ping());
    }
}
