
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class JedisTest {

    @Test
    public void demo1(){
        //连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(30);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);
        //获取连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379,2000,"icepoint123");
        Jedis jedis = jedisPool.getResource();
        jedis.set("name","张三");
    }

    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<Integer>();
        List<Integer> list2=new ArrayList<Integer>();
        for (int i =0;i<=5;i++){
            list1.add(i);
            list2.add(i);
        }
        for (Integer i : list1) {
            System.out.println("i===="+i);
            for (Integer j : list2) {
                System.out.println("j===="+j);
                if(j==1)
                    return;
            }
        }
    }
}
