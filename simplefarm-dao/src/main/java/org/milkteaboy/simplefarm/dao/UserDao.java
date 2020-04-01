package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.User;

/**
 * 用户DAO
 */
public interface UserDao {

    int insert(User user);
    int update(User user);
    User selectById(Integer userId);
    User selectByAccountId(Integer accountId);
    User selectByNickname(String nickname);

}
