package gzq.byd.com.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Random;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.31.83",6379);
        jedis.auth("102938");

//        jedis.
//        jedis.lpush("b","hello","\0","\t","[12345566]");
//        String b1 = jedis.lpop("b");
//        System.out.println(b);
//        jedis.lpush("b", String.valueOf(new Random().nextInt()));
        List<String> b = jedis.lrange("b", 0, -1);

        jedis.close();

        System.out.println(b);
    }
}
