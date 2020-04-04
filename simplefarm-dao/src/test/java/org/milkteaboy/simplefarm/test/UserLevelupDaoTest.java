package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserLevelupDao;
import org.milkteaboy.simplefarm.entity.UserLevelup;

import javax.annotation.Resource;

public class UserLevelupDaoTest extends Test {

    @Resource
    private UserLevelupDao userLevelupDao;

    @org.junit.Test
    public void testSelect() {
        UserLevelup userLevelup = userLevelupDao.selectByLevel(1);
        Assert.assertEquals(userLevelup.getExp().toString(), "100");
    }

}
