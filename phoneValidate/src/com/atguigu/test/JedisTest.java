package com.atguigu.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {

        Set<HostAndPort> set =new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.44.129",6389));
        set.add(new HostAndPort("192.168.44.129",6379));
        set.add(new HostAndPort("192.168.44.129",6380));



        JedisCluster jedisCluster=new JedisCluster(set);//等价于 jedis

      //  jedisCluster.set("k1", "v1");
        System.out.println(jedisCluster.get("k1"));



       /* //使用jedis技术连接redis
        Jedis jedis=new Jedis("192.168.44.129",6379);//connection
        String res = jedis.ping();
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            if(jedis.type(key).equals("string")){
                String strVal = jedis.get(key);
                System.out.println(strVal);
            }
            System.out.println("set====================");
            if(jedis.type(key).equals("set")){
                Set<String> smembers = jedis.smembers(key);
                for (String smember : smembers) {
                    System.out.println(smember);
                }
            }
            System.out.println("list==========");
            if(jedis.type(key).equals("list")){
                List<String> lrange = jedis.lrange(key, 0, -1);
                for (String s : lrange) {
                    System.out.println(s);
                }
            }
        }
        */
    }
}
