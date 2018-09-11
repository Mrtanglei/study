package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SucessSeckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SucessSeckillDaoTest {

    private Logger LOGGER = LoggerFactory.getLogger(SucessSeckillDaoTest.class);

    @Autowired
    private SucessSeckillDao sucessSeckillDao;

    @Test
    public void insertSucessSeckill() {
        int insertCount = sucessSeckillDao.insertSucessSeckill(100,"1323423");
        LOGGER.info("insert count = {}",insertCount);
    }

    @Test
    public void qeuryByIdWithSeckill() {
        SucessSeckill sucessSeckill = sucessSeckillDao.queryByIdWithSeckill(100,"1323423");
        LOGGER.info(sucessSeckill.toString());
        LOGGER.info(sucessSeckill.getSeckill().toString());
    }
}