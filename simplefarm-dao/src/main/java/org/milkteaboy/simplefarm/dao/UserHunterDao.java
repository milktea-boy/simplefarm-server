package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.UserHunter;

/**
 * 用户猎人小屋信息DAO
 */
public interface UserHunterDao {

    int insert(UserHunter userHunter);
    int update(UserHunter userHunter);
    UserHunter selectByUserId(Integer userId);

}
