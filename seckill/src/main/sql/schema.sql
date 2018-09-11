drop database if exists seckill;

CREATE database seckill;

use seckill;

-- 创建秒杀库存表
create table seckill(
	  id bigint(20) not null auto_increment comment '商品库存id',
    name varchar(120) not null comment '商品名称',
    number int(11) not null comment '商品库存',
    start_time timestamp not null comment '秒杀开始时间',
    end_time timestamp not null comment '秒杀结束时间',
    create_time timestamp not null default current_timestamp comment '创建时间',
    primary key(id),
    key idx_seckill_time(start_time,end_time),
    key idx_create_time(create_time)
)engine=InnoDB auto_increment = 100 default charset=utf8 comment '秒杀库存表';

-- 初始化数据
insert into seckill(name,number,start_time,end_time)
values('1000元秒杀iPhone7',100,'2018-07-03 00:00:00','2018-07-03 23:59:59'),
('1000元秒杀iPad',100,'2018-07-03 00:00:00','2018-07-03 23:59:59'),
('100元秒杀小米6',100,'2018-07-03 00:00:00','2018-07-03 23:59:59'),
('300元秒杀小米7',100,'2018-07-03 00:00:00','2018-07-03 23:59:59');

-- 秒杀成功明细表
-- 用户登录认证相关信息
create table sucess_seckill(
	  seckill_id bigint(20) not null comment '秒杀商品id',
    user_phone varchar(11) not null comment '用户手机号',
    state tinyint not null comment '状态表示：-1：无效；0：成功；1：已付款',
    create_time timestamp not null default current_timestamp comment '创建时间',
    primary key(seckill_id,user_phone),
    key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment '秒杀成功明细表';