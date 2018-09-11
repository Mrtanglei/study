package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillDaoTest.class);

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        int updateCount = seckillDao.reduceNumber(100,new Date());
        LOGGER.info("update count = {}",updateCount);
    }

    @Test
    public void queryById() {
        Seckill seckill = seckillDao.queryById(100);
        LOGGER.info(seckill.toString());
        assertTrue(seckill != null && seckill.getId() == 100);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckillList = seckillDao.queryAll(0,100);
        for (Seckill seckill: seckillList) {
            LOGGER.info(seckill.toString());
        }
    }
}