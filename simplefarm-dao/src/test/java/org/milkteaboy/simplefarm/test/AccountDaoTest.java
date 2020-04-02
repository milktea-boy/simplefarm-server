package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.AccountDao;
import org.milkteaboy.simplefarm.entity.Account;

import javax.annotation.Resource;

/**
 * AccountDao测试类
 */
public class AccountDaoTest extends Test {

    @Resource
    private AccountDao accountDao;

    @org.junit.Test
    public void testInsert() {
        Account account = new Account();
        account.setUsername("test");
        account.setPassword("test");
        int result = accountDao.insert(account);

        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        Account account0 = accountDao.selectById(13);
        Assert.assertEquals(account0.getUsername(), "test");

        Account account1 = accountDao.selectByUsernameAndPassword("test", "123");
        Assert.assertEquals(account1.getUsername(), "test");

        Account account2 = accountDao.selectByUsername("test");
        Assert.assertEquals(account2.getPassword(), "123");
    }

    @org.junit.Test
    public void testUpdate() {
        Account account = accountDao.selectById(11);
        account.setPassword("123");
        int result = accountDao.updatePassword(account);
        Assert.assertEquals(result, 1);
    }

}
