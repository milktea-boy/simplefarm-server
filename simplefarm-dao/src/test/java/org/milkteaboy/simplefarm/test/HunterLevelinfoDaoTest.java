package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.HunterLevelinfoDao;
import org.milkteaboy.simplefarm.entity.HunterLevelinfo;

import javax.annotation.Resource;

public class HunterLevelinfoDaoTest extends Test {

    @Resource
    private HunterLevelinfoDao hunterLevelinfoDao;

    @org.junit.Test
    public void testSelect() {
        HunterLevelinfo hunterLevelinfo = hunterLevelinfoDao.selectByLevel(1);
        Assert.assertEquals(hunterLevelinfo.getMaxReceiveCount().intValue(), 4);
    }

}
