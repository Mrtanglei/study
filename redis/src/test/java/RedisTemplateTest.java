import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:application-redis.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTemplateTest {

    @Autowired
    RedisTemplate<String, Employee> redisTemplate;


    @Test
    public void demo1() {
        ValueOperations<String, Employee> operations = redisTemplate.opsForValue();
        operations.set("A", new Employee("aaa", 12));
        Employee a = operations.get("A");
        System.out.println(a.toString());
        //        EmpoyeeRedisDao redisDao = new EmpoyeeRedisDao();
        //        redisDao.setObjectValue("A", new Employee("aaa", 12));
        //        redisDao.setObjectValue("B", new Employee("bbb", 13));
        //        System.out.println(redisDao.getObjectValue("B"));
    }
}
