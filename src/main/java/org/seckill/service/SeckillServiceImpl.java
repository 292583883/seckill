package org.seckill.service;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2016/5/22.
 */
//@Component
//@Dao
@Service
public class SeckillServiceImpl implements  SeckillService {

    //md5盐值
    private final String salt = "dlfjasjfoijsidjfoij";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Inject
//    @Resource
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,10);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = getById(seckillId);
        if (seckill == null){
            return  new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime());
        }
        String md5 = getMd5(seckillId);//TODO
        return new Exposer(true, md5, seckillId );
    }

    private String getMd5(long seckillId) {
        String base = seckillId+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    /**
     * 声明式事务的优点:
     *1.开发团队达成一致约定,明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短,不要穿插其他网络RPC/HTTP请求和io操作或者剥离到事务方法外部
     * 3.不是所有的方法阿斗需要事务,如:
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    @Transactional
    public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite...");
        }
        //执行秒杀逻辑:减库存+记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount < 0 ){
                //没有更新到记录,秒杀结束
                throw new SeckillCloseException("seckill is closed...");
            }else{
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
                if (insertCount <= 0 ){
                    throw new RepeatKillException("seckill repeat...");
                }else{
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e){
            throw e;
        }
        catch (RepeatKillException e){
            throw e;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }

    public SeckillDao getSeckillDao() {
        return seckillDao;
    }

    public SuccessKilledDao getSuccessKilledDao() {
        return successKilledDao;
    }
}
