package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.HunterService;
import org.milkteaboy.simplefarm.service.dto.HunterInfo;

import javax.annotation.Resource;

public class HunterServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private HunterService hunterService;

    @org.junit.Test
    public void testInitHunter() {
        User user = userDao.selectById(1);
        hunterService.initHunterInfo(user);
    }

    @org.junit.Test
    public void testGetHunterInfo() {
        User user = userDao.selectById(1);
        HunterInfo hunterInfo = hunterService.getHunterInfo(user);
        Assert.assertEquals(hunterInfo.getState(), 0);
    }

    @org.junit.Test
    public void testSend() {
        User user = userDao.selectById(1);
        hunterService.sendHunter(user);
    }

    @org.junit.Test
    public void testReceive() {
        User user = userDao.selectById(1);
        hunterService.receiveHunter(user);
    }

}
