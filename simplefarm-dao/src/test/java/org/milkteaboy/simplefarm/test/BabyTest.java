package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.BabyDao;
import org.milkteaboy.simplefarm.entity.Baby;

import javax.annotation.Resource;
import java.util.List;

public class BabyTest extends Test {

    @Resource
    private BabyDao babyDao;

    @org.junit.Test
    public void testSelect() {
        Baby baby = babyDao.selectById(0);
        Assert.assertEquals(baby.getPrice().toString(), "1");

        List<Baby> babyList = babyDao.selectAll();
        Assert.assertEquals(babyList.size(), 2);
    }

}
