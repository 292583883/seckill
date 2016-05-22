package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * @author Administrator
 * @date 2016/5/22.
 * 完全站在使用者的角度设计接口
 * 1)方法定义粒度,非常明确 例如:秒杀系统->执行秒杀
 * 2)参数越简练,
 * 3)返回类型,retrun 类型/exception 比较友好,不要使用map和entity(参数还不够)
 *
 */

public interface SeckillService {
    /**
     * 获取所有秒杀列表
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启是输出秒杀哦接口地址,
     * 否则输出系统时间和秒杀哦时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
