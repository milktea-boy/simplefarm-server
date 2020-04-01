package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;

/**
 * 账号Service
 */
public interface AccountService {

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User login(String username, String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 用户
     */
    User register(String username, String password, String nickname);

    /**
     * 修改密码
     * @param accountId 账号ID
     * @param password 密码
     * @return 是否成功
     */
    boolean changePassword(Integer accountId, String password);

}
