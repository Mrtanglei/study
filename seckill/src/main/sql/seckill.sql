-- 秒杀执行存储过程
-- row_count():返回上一条修改sql影响的行数 0(未修改数据)，>0(表示修改的行数)<0(sql未执行/slq错误)
DELIMITER $$
CREATE PROCEDURE execute_seckill(in v_seckill_id BIGINT,in v_phone VARCHAR(11), in seckill_time DATETIME ,OUT  r_result int)
  BEGIN
    DECLARE update_count int DEFAULT 0;
    START TRANSACTION ;
    insert IGNORE into sucess_seckill (seckill_id, user_phone, create_time)
      VALUES (v_seckill_id,v_phone,seckill_time);
    SELECT row_count() into update_count;
    IF update_count = 0 THEN
      ROLLBACK ;
      set r_result = -1;
    ELSEIF update_count < 0 THEN
      ROLLBACK ;
      set r_result = -2;
    ELSE
      UPDATE seckill set number = number -1
      WHERE id = v_seckill_id and start_time <= seckill_time and end_time >= seckill_time and number > 0;
      SELECT row_count() into update_count;
      IF update_count = 0 THEN
        ROLLBACK ;
        SET r_result = 0;
      ELSEIF update_count < 0 THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;
  END $$