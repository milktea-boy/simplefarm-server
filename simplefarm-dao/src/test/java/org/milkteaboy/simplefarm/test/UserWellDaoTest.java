package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserWellDao;
import org.milkteaboy.simplefarm.entity.UserWell;

import javax.annotation.Resource;
import java.util.Date;

public class UserWellDaoTest extends Test {

    @Resource
    private UserWellDao userWellDao;

    @org.junit.Test
    public void testInsert() {
        UserWell userWell = new UserWell();
        userWell.setUserId(1);
        userWell.setReapDatetime(new Date());

        int result = userWellDao.insert(userWell);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testUpdate() {
        UserWell userWell = userWellDao.selectByUserId(1);

        int result = userWellDao.update(userWell);
        Assert.assertEquals(result, 1);
    }

}
