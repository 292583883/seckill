package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2016/5/22.
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param KillTime
     * @return 插入的行数>1 成功/0失败
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date KillTime);

    /**
     * 根据秒杀id获取对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量获取秒扫列表
     * @param offset
     * @param limit
     * @return
     *java没有保存形参记录,
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
