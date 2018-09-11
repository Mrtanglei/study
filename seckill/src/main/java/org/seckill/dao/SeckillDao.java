package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeckillDao {

    /**
     * 减库存
     *
     * @param id
     * @param killTime
     * @return 如果影响行数>=1,表示更新数据的行数
     */
    int reduceNumber(@Param("id") long id, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     *
     * @param id
     * @return
     */
    Seckill queryById(long id);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paremes
     */
    void killByProcedure(Map<String, Object> paremes);
}
