package com.msb.jedis;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JedisTest {

    /**
     * jedis的创建与链接
     */
    @Test
    public void testStandalone(){

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("name","msb-standalone");

        String value = jedis.get("name");

        System.out.println(value);

    }

    /**
     * jedisPool  数据连接池的创建与使用
     */
    @Test
    public void testJedisPoll(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(3);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);

        Jedis jedis = jedisPool.getResource();

        jedis.set("name","zhangsan");
        System.out.println(jedis.get("name"));

    }

    /**
     * redis集群的使用
     */
    @Test
    public void testCluter(){

        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("127.0.0.1",6379));
        set.add(new HostAndPort("127.0.0.1",7001));
        set.add(new HostAndPort("127.0.0.1",7002));
        set.add(new HostAndPort("127.0.0.1",7003));
        set.add(new HostAndPort("127.0.0.1",7004));
        set.add(new HostAndPort("127.0.0.1",7005));

        JedisCluster jedisCluster = new JedisCluster(set);

        jedisCluster.set("name","lisi");
        System.out.println(jedisCluster.get("name"));

    }

}
