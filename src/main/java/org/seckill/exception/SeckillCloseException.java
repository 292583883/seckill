package org.seckill.exception;

/**
 * 秒杀关闭异常
 * @author Administrator
 * @date 2016/5/22.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
