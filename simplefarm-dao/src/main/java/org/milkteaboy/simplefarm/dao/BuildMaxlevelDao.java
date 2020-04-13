package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.BuildMaxlevel;

import java.util.List;

/**
 * 建筑最大等级DAO
 */
public interface BuildMaxlevelDao {

    BuildMaxlevel selectByUserLevelAndBuildId(@Param("userLevel") Integer userLevel, @Param("buildId") Integer buildId);
    List<BuildMaxlevel> selectByUserLevel(Integer userLevel);

}
