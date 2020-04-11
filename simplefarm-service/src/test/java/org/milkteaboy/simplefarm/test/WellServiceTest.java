package org.milkteaboy.simplefarm.test;

import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.WellService;

import javax.annotation.Resource;

public class WellServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private WellService wellService;

    @org.junit.Test
    public void testInit() {
        User user = userDao.selectById(1);
        wellService.initWellInfo(user);
    }

    @org.junit.Test
    public void testReap() {
        User user = userDao.selectById(1);
        int count = wellService.reap(user);
        System.out.println(count);
    }

}
