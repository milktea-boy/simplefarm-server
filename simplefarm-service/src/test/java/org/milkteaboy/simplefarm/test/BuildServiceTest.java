package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.BuildService;

import javax.annotation.Resource;

public class BuildServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private BuildService buildService;

    @org.junit.Test
    public void testGetUpgradePrice() {
        User user = userDao.selectById(1);
        int price = buildService.getUpgradePrice(user, 0);
        Assert.assertEquals(price, 200);
    }

    @org.junit.Test
    public void testUpgrade() {
        User user = userDao.selectById(1);
        buildService.upgrade(user, 0);
    }

    @org.junit.Test
    public void testInitBuildInfo() {
        User user = userDao.selectById(1);
        buildService.initBuildInfo(user);
    }

}
