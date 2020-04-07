package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.LivestockLevelinfoDao;
import org.milkteaboy.simplefarm.entity.LivestockLevelinfo;

import javax.annotation.Resource;

public class LivestockLevelinfoDaoTest extends Test {

    @Resource
    private LivestockLevelinfoDao livestockLevelinfoDao;

    @org.junit.Test
    public void testSelect() {
        LivestockLevelinfo livestockLevelinfo = livestockLevelinfoDao.selectByLevel(1);
        Assert.assertEquals(livestockLevelinfo.getMaxPopulation().toString(), "5");
    }

}
