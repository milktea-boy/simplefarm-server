package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.AccountService;

import javax.annotation.Resource;

/**
 * AccountService测试类
 */
public class AccountServiceTest extends Test {

    @Resource
    private AccountService accountService;

    @org.junit.Test
    public void testRegister() {
        accountService.register("test", "123", "测试");
    }

    @org.junit.Test
    public void testLogin() {
        User user = accountService.login("test", "123");
        Assert.assertEquals(user.getNickname(), "测试");
    }

}
