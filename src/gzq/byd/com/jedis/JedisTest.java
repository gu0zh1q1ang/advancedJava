package gzq.byd.com.jedis;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.77.177",6379);
        jedis.auth("102938");

        // 存hash
        /*Map<String,String> todos = new HashMap<>();
        todos.put("name", "张三");
        todos.put("age", "13");
        todos.put("desc", "{\"from\":\"山西\t大同\"}");

        jedis.hmset("student", todos);*/

        jedis.hset("student", "desc", "{\"from\":\"山西\t大同\"}");
        Map<String, String> student = jedis.hgetAll("student");
        for (Map.Entry<String, String> stringStringEntry : student.entrySet()) {
            System.out.println(stringStringEntry.getKey()+":"+stringStringEntry.getValue());
        }
        jedis.close();
    }
}
