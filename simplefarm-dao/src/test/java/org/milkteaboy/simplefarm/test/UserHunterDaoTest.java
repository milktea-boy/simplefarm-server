package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserHunterDao;
import org.milkteaboy.simplefarm.entity.UserHunter;

import javax.annotation.Resource;
import java.util.Date;

public class UserHunterDaoTest extends Test {

    @Resource
    private UserHunterDao userHunterDao;

    @org.junit.Test
    public void testInsert() {
        UserHunter userHunter = new UserHunter();
        userHunter.setUserId(1);
        userHunter.setSendDatetime(new Date());

        int result = userHunterDao.insert(userHunter);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testUpdate() {
        UserHunter userHunter = userHunterDao.selectByUserId(1);
        userHunter.setSendDatetime(new Date());

        int result = userHunterDao.update(userHunter);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        UserHunter userHunter = userHunterDao.selectByUserId(1);
        System.out.println(userHunter.getSendDatetime());
    }

}
