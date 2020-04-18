package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.dto.UserDetailInfo;
import org.milkteaboy.simplefarm.service.dto.UserInfo;

import javax.annotation.Resource;

public class UserServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private UserService userService;

    @org.junit.Test
    public void testGetInfo() {
        User user = userDao.selectById(1);
        UserInfo userInfo = userService.getUserInfo(user);
        System.out.println(userInfo);
    }

    @org.junit.Test
    public void testGetDetailInfo() {
        User user = userDao.selectById(2);
        UserDetailInfo userDetailInfo = userService.getUserDetailInfo(user);
        Assert.assertEquals(userDetailInfo.getNeedExp(), 100);
    }

}
