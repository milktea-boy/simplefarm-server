package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.UserLevelup;

/**
 * 用户升级信息DAO
 */
public interface UserLevelupDao {

    UserLevelup selectByLevel(Integer level);

}
