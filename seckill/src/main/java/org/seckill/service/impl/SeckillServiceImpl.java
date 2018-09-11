package org.seckill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SucessSeckillDao;
import org.seckill.dao.redis.SeckillRedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SucessSeckill;
import org.seckill.enums.SeckillState;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillColseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //MD5盐值字符串，用于混淆MD5
    private final String slat = "dsafsfa&**^$%#$vds.,[;sdada";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SucessSeckillDao sucessSeckillDao;

    @Autowired
    private SeckillRedisDao seckillRedisDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 100);
    }

    @Override
    public Seckill getSeckillById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = seckillRedisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = getSeckillById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            }
            seckillRedisDao.putSeckill(seckill);
        }
        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < start.getTime() || nowTime.getTime() > end.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), start.getTime(), end.getTime());
        }
        //转化特定字符的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, String userPhone, String md5) throws SeckillException, RepeatKillException, SeckillColseException {
        if (md5 == null || !(getMD5(seckillId).equals(md5))) {
            throw new SeckillException("数据篡改");
        }
        try {
            int insertCount = sucessSeckillDao.insertSucessSeckill(seckillId, userPhone);
            if (insertCount <= 0)
                throw new RepeatKillException("重复秒杀");
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount <= 0)
                throw new SeckillColseException("秒杀不成功");
            SucessSeckill sucessSeckill = sucessSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
            return new SeckillExecution(seckillId, SeckillState.SUCCESS, sucessSeckill);
        } catch (SeckillColseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            throw new SeckillException("秒杀失败：" + e.getMessage());
        }
    }

    @Override
    public SeckillExecution executeSeckillByProcedure(long seckillId, String userPhone, String md5) {
        if (md5 == null || !(getMD5(seckillId).equals(md5))) {
            throw new SeckillException("数据篡改");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("seckillId", seckillId);
        map.put("userPhone", userPhone);
        map.put("killTime", new Date());
        map.put("result", null);
        try {
            //当存储过程执行完成后result被赋值
            seckillDao.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SucessSeckill sucessSeckill = sucessSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillState.SUCCESS, sucessSeckill);
            } else {
                return new SeckillExecution(seckillId, SeckillState.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillState.INNSERT_ERROR);
        }
    }
}
