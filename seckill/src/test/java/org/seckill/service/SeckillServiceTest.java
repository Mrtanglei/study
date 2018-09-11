package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillColseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getSeckillById() {
        Seckill seckill = seckillService.getSeckillById(100);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void executeSeckill() {
        Exposer exposer = seckillService.exportSeckillUrl(101);
        if (exposer != null && exposer.isExposed()) {
            logger.info("=============exposer={}", exposer);
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(101, "132335345", exposer.getMd5());
                logger.info("=============seckillExecution={}", seckillExecution);
            } catch (SeckillColseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }

        } else {
            logger.warn("=============秒杀未开启,exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckillByProcedure() {
        Exposer exposer = seckillService.exportSeckillUrl(101);
        if (exposer != null && exposer.isExposed()) {
            logger.info("=============exposer={}", exposer);
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckillByProcedure(101, "12432423342", exposer.getMd5());
                logger.info("=============seckillExecution={}", seckillExecution);
            } catch (SeckillColseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }

        } else {
            logger.warn("=============秒杀未开启,exposer={}", exposer);
        }
    }
}