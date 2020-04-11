package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.GroundService;
import org.milkteaboy.simplefarm.service.dto.GroundInfo;

import javax.annotation.Resource;

public class GroundServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private GroundService groundService;

    @org.junit.Test
    public void testInitGroundInfo() {
        User user = userDao.selectById(3);
        groundService.initGroundInfo(user);
    }

    @org.junit.Test
    public void testGetGroundInfo() {
        User user = userDao.selectById(1);
        GroundInfo groundInfo = groundService.getGroundInfo(user, 0);
        Assert.assertEquals(groundInfo.getState(), 2);
    }

    @org.junit.Test
    public void testSow() {
        User user = userDao.selectById(3);
        groundService.sow(user, 0,0);
    }

    @org.junit.Test
    public void testWater() {
        User user = userDao.selectById(3);
        groundService.water(user, 0);
    }

    @org.junit.Test
    public void testReap() {
        User user = userDao.selectById(3);
        groundService.reap(user, 0);
    }

}
