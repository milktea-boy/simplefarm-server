package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.SeedDao;
import org.milkteaboy.simplefarm.entity.Seed;

import javax.annotation.Resource;
import java.util.List;

public class SeedTest extends Test {

    @Resource
    private SeedDao seedDao;

    @org.junit.Test
    public void testSelect() {
        Seed seed = seedDao.selectById(0);
        Assert.assertEquals(seed.getReapInterval().toString(), "30");

        List<Seed> seedList = seedDao.selectAll();
        Assert.assertEquals(seedList.size(), 2);
    }
}
