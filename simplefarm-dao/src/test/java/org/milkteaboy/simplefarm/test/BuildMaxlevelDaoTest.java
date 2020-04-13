package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.BuildMaxlevelDao;
import org.milkteaboy.simplefarm.entity.BuildMaxlevel;

import javax.annotation.Resource;
import java.util.List;

public class BuildMaxlevelDaoTest extends Test {

    @Resource
    private BuildMaxlevelDao buildMaxlevelDao;

    @org.junit.Test
    public void testSelect() {
        BuildMaxlevel buildMaxlevel = buildMaxlevelDao.selectByUserLevelAndBuildId(1, 0);
        Assert.assertEquals(buildMaxlevel.getMaxLevel().toString(), "1");

        List<BuildMaxlevel> buildMaxlevels = buildMaxlevelDao.selectByUserLevel(1);
        System.out.println(buildMaxlevels);
    }

}
