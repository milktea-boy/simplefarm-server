package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.HunterLevelinfo;

/**
 * 猎人小屋等级信息DAO
 */
public interface HunterLevelinfoDao {

    HunterLevelinfo selectByLevel(Integer level);

}
