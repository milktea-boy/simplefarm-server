package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.AccountDao;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.Account;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.AccountService;
import org.milkteaboy.simplefarm.service.exception.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountService实现类
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User login(String username, String password) {
        if (username == null || username.equals("") || password == null || password.equals(""))
            return null;

        Account account = accountDao.selectByUsernameAndPassword(username, password);
        if (account == null)
            return null;

        User user = userDao.selectByAccountId(account.getId());

        return user;
    }

    @Transactional
    @Override
    public User register(String username, String password, String nickname) {
        if (username == null || username.equals("") || password == null || password.equals("") || nickname == null || nickname.equals(""))
            return null;

        Account account = accountDao.selectByUsernameAndPassword(username, password);
        if (account != null)
            return null;

        account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        int accountResult = accountDao.insert(account);
        if (accountResult < 1)
            throw new AccountException("创建账号失败");

        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setLevel(1);
        user.setExp(0);
        user.setCoin(100);
        int userResult = userDao.insert(user);
        if (userResult < 1)
            throw new AccountException("创建用户失败");

        return user;
    }

    @Transactional
    @Override
    public boolean changePassword(Integer accountId, String password) {
        if (accountId == null || password == null || password.equals(""))
            return false;

        Account account = accountDao.selectById(accountId);
        if (account == null)
            throw new AccountException("查找不到此账号：" + accountId);

        account.setPassword(password);
        int result = accountDao.updatePassword(account);
        if (result < 1)
            return false;

        return true;
    }
}
