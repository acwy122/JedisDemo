package com.msb.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {


    @Test
    public void testStandalone(){

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("name","msb-standalone");

        String value = jedis.get("name");

        System.out.println(value);

    }
}
