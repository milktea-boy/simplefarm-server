package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.UserGround;

import java.util.List;

/**
 * 用户地块DAO
 */
public interface UserGroundDao {

    int insert(UserGround userGround);
    int update(UserGround userGround);
    UserGround selectByUserIdAndIndex(@Param("userId") Integer userId, @Param("index") Integer index);
    List<UserGround> selectByUserId(Integer userId);

}
