package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.BuildLevelinfoDao;
import org.milkteaboy.simplefarm.entity.BuildLevelinfo;

import javax.annotation.Resource;

public class BuildLevelinfoDaoTest extends Test {

    @Resource
    private BuildLevelinfoDao buildLevelinfoDao;

    @org.junit.Test
    public void testSelect() {
        BuildLevelinfo buildLevelinfo = buildLevelinfoDao.selectByBuildIdAndLevel(0, 1);
        Assert.assertEquals(buildLevelinfo.getPrice().toString(), "100");
    }

}
