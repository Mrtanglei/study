package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillColseException;
import org.seckill.exception.SeckillException;

import java.util.List;

public interface SeckillService {

    /**
     * 查询所有的秒杀商品
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀商品
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, String userPhone, String md5) throws SeckillException, RepeatKillException, SeckillColseException;

    /**
     * 执行秒杀操作 by存储过程
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckillByProcedure(long seckillId, String userPhone, String md5);
}
