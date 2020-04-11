package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.LivestockService;
import org.milkteaboy.simplefarm.service.dto.LivestockInfo;

import javax.annotation.Resource;

public class LivestockServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private LivestockService livestockService;

    @org.junit.Test
    public void testGetLivestockInfo() {
        User user = userDao.selectById(1);
        LivestockInfo livestockInfo = livestockService.getLivestockInfo(user, 0);
        Assert.assertEquals(livestockInfo.getState(), 0);
    }

    @org.junit.Test
    public void testBreed() {
        User user = userDao.selectById(3);
        livestockService.breed(user, 1, 0, 5);
    }

    @org.junit.Test
    public void testFeed() {
        User user = userDao.selectById(3);
        livestockService.feed(user, 1);
    }

}
