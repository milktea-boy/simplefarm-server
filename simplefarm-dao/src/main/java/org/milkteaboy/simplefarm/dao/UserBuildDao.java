package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.UserBuild;

import java.util.List;

/**
 * 用户建筑信息DAO
 */
public interface UserBuildDao {

    int insert(UserBuild userBuild);
    int update(UserBuild userBuild);
    UserBuild selectByUserIdAndBuildId(@Param("userId") Integer userId, @Param("buildId") Integer buildId);
    List<UserBuild> selectByUserId(Integer userId);

}
