package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @date 2016/5/22.
 * 配置sring和junit整合
 * junit启动是加载springIoc容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    //注入dao实现类
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        long id = 1000l;
        Date dateTwoYear = new Date(2017,1,1);
        assertTrue(seckillDao.reduceNumber(id, dateTwoYear) == 0);

        int num = seckillDao.queryById(id).getNumber();
        assertTrue(seckillDao.reduceNumber(id, new Date()) == 1);
        assertTrue(num - seckillDao.queryById(id).getNumber() == 1);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000l;
        Seckill seckill = seckillDao.queryById(id);
        assertNotNull(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList= seckillDao.queryAll(0,100);
        assertNotNull(seckillList);
        assertTrue(seckillList.size() > 0);

    }

}