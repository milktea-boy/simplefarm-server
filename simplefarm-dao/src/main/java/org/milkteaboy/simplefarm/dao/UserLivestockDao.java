package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.UserLivestock;

/**
 * 用户畜舍信息DAO
 */
public interface UserLivestockDao {

    int insert(UserLivestock userLivestock);
    int update(UserLivestock userLivestock);
    UserLivestock selectByUserIdAndBuildId(@Param("userId") Integer userId, @Param("buildId") Integer buildId);

}
