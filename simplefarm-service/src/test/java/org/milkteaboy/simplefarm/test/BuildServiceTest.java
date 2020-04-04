package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.service.BuildService;

import javax.annotation.Resource;

public class BuildServiceTest extends Test {

    @Resource
    private BuildService buildService;

    @org.junit.Test
    public void testGetUpgradePrice() {
        int price = buildService.getUpgradePrice(1, 0);
        Assert.assertEquals(price, 200);
    }

    @org.junit.Test
    public void testUpgrade() {
        buildService.upgrade(1, 0);
    }

}
