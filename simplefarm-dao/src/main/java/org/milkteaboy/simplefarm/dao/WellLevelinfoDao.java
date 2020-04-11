package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.WellLevelinfo;

/**
 * 水井等级信息DAO
 */
public interface WellLevelinfoDao {

    WellLevelinfo selectByLevel(Integer level);

}
