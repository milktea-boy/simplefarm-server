package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.WellLevelinfoDao;
import org.milkteaboy.simplefarm.entity.WellLevelinfo;

import javax.annotation.Resource;

public class WellLevelinfoDaoTest extends Test {

    @Resource
    private WellLevelinfoDao wellLevelinfoDao;

    @org.junit.Test
    public void testSelect() {
        WellLevelinfo wellLevelinfo = wellLevelinfoDao.selectByLevel(1);
        Assert.assertEquals(wellLevelinfo.getReapInterval().intValue(), 30);
    }

}
