package org.seckill.dao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillRedisDaoTest {

    @Autowired
    private SeckillRedisDao seckillRedisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckillRedis() {
        long id = 100;
        Seckill seckill = seckillRedisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                seckillRedisDao.putSeckill(seckill);
                System.out.println(seckillRedisDao.getSeckill(id));
            }
        }
    }
}