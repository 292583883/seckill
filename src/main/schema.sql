-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用
USE seckill;

-- 创建秒杀库存表
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` INT NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY(seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=utf8 COMMENT='秒杀库存表'

-- 初始化数据
INSERT INTO seckill (name,number,start_time,end_time)
  VALUES
('1000元秒杀1phone6',100,'2015-11-01 00:00:00','2015-11-01 00:00:00'),
('100元秒杀app',100,'2015-11-01 00:00:00','2015-11-01 00:00:00'),
('500元秒杀红米',100,'2015-11-01 00:00:00','2015-11-01 00:00:00');

-- 秒杀哦成功明细表
-- 用户登录认证相关信息
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL  COMMENT '秒杀商品id' ,
  `user_phone` BIGINT NOT NULL  COMMENT '用户手机',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标记:-1无效,0成功,1已付款',
  `create_time` TIMESTAMP NOT NULL  COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone),
  key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='秒杀成功明细表'


