package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @date 2016/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao dao;

    @Test
    public void insertSuccessKilled() throws Exception {
        int updateCount = dao.insertSuccessKilled(1000l,1888888888l);
        assertTrue(updateCount == 1);

        int updateCount1= dao.insertSuccessKilled(1000l,1888888888l);
        assertTrue(updateCount1 == 0);

    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        dao.queryByIdWithSeckill(1000l, 1888888888l);
    }

}