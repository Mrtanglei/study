package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SucessSeckill;

public interface SucessSeckillDao {


    /**
     * 新增秒杀记录
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSucessSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

    /**
     * 根据id查询SucessSeckill并携带秒杀产品实体
     *
     * @param seckillId
     * @return
     */
    SucessSeckill queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);
}
