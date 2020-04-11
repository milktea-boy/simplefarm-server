package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.UserWell;

/**
 * 用户水井信息DAO
 */
public interface UserWellDao {

    int insert(UserWell userWell);
    int update(UserWell userWell);
    UserWell selectByUserId(Integer userId);

}
