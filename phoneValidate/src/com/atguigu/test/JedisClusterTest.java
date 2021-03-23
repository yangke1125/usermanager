package com.atguigu.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest  {
    public static void main(String[] args) {
        Set<HostAndPort> set =new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.153.128",6379));
        JedisCluster jedisCluster=new JedisCluster(set);

        jedisCluster.set("k1", "v1");
        System.out.println(jedisCluster.get("k1"));

    }
}
