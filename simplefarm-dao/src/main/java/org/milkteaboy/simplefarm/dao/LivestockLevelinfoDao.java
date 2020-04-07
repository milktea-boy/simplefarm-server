package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.LivestockLevelinfo;

/**
 * 畜舍等级信息DAO
 */
public interface LivestockLevelinfoDao {

    LivestockLevelinfo selectByLevel(Integer level);

}
