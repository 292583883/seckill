package org.seckill.exception;

import org.seckill.entity.SuccessKilled;

/**
 * 秒杀
 * @author Administrator
 * @date 2016/5/22.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}
