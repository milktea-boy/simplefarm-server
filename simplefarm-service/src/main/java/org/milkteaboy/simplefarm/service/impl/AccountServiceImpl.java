package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.AccountDao;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.Account;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.AccountService;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.exception.AccountException;
import org.milkteaboy.simplefarm.service.util.MD5Util;
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
    @Autowired
    private BuildService buildService;

    @Override
    public User login(String username, String password) {
        if (username == null || username.equals("") || password == null || password.equals(""))
            throw new AccountException("用户名不能为空");
        else if (password == null || password.equals(""))
            throw new AccountException("密码不能为空");

        password = MD5Util.getMD5(password);

        Account account = accountDao.selectByUsernameAndPassword(username, password);
        if (account == null)
            throw new AccountException("用户名或密码错误");

        User user = userDao.selectByAccountId(account.getId());
        if (user == null)
            throw new AccountException("获取用户信息错误");

        return user;
    }

    @Transactional
    @Override
    public void register(String username, String password, String nickname) {
        if (username == null || username.equals(""))
            throw new AccountException("用户名不能为空");
        else if (password == null || password.equals(""))
            throw new AccountException("密码不能为空");
        else if (nickname == null || nickname.equals(""))
            throw new AccountException("昵称不能为空");

        Account account = accountDao.selectByUsername(username);
        if (account != null)
            throw new AccountException("用户名已被注册");

        password = MD5Util.getMD5(password);

        account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        int accountResult = accountDao.insert(account);
        if (accountResult < 1)
            throw new AccountException("注册失败");

        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setLevel(1);
        user.setExp(0);
        user.setCoin(100);
        int userResult = userDao.insert(user);
        if (userResult < 1)
            throw new AccountException("注册失败");

        // 插入建筑信息
        buildService.initBuildInfo(user);
    }

    @Transactional
    @Override
    public void changePassword(Integer accountId, String password) {
        if (accountId == null)
            throw new AccountException("账户ID不能为空");
        else if (password == null || password.equals(""))
            throw new AccountException("密码不能为空");

        Account account = accountDao.selectById(accountId);
        if (account == null)
            throw new AccountException("无此账号");

        password = MD5Util.getMD5(password);

        account.setPassword(password);
        int result = accountDao.updatePassword(account);
        if (result < 1)
            throw new AccountException("修改密码失败");
    }
}
