package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.BuildLevelinfo;

/**
 * 建筑等级信息DAO
 */
public interface BuildLevelinfoDao {

    BuildLevelinfo selectByBuildIdAndLevel(@Param("buildId") Integer buildId, @Param("level") Integer level);

}
