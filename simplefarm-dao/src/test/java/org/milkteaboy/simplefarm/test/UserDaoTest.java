package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.Account;
import org.milkteaboy.simplefarm.entity.User;

import javax.annotation.Resource;

/**
 * UserDao测试类
 */
public class UserDaoTest extends Test {

    @Resource
    private UserDao userDao;

    @org.junit.Test
    public void testInsert() {
        User user = new User();
        Account account = new Account();
        account.setId(12);
        user.setAccount(account);
        user.setNickname("测试");
        user.setLevel(2);
        user.setExp(99);
        user.setCoin(100);
        int result = userDao.insert(user);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        User user0 = userDao.selectById(1);
        Assert.assertEquals(user0.getNickname(), "测试");

        User user1 = userDao.selectByAccountId(11);
        Assert.assertEquals(user1.getNickname(), "测试");

        User user2 = userDao.selectByNickname("测试");
        Assert.assertEquals(user2.getAccount().getId(), new Integer(11));
    }

    @org.junit.Test
    public void testUpdate() {
        User user = userDao.selectById(1);
        user.setCoin(50);
        int result = userDao.update(user);
        Assert.assertEquals(result, 1);
    }

}
