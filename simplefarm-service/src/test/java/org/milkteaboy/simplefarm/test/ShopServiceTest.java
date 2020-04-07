package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.ShopService;
import org.milkteaboy.simplefarm.service.dto.ShopBabyInfo;
import org.milkteaboy.simplefarm.service.dto.ShopFoodInfo;
import org.milkteaboy.simplefarm.service.dto.ShopGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.ShopSeedInfo;

import javax.annotation.Resource;
import java.util.List;

public class ShopServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private ShopService shopService;

    @org.junit.Test
    public void testBuyBaby() {
        User user = userDao.selectById(2);
        shopService.buyBaby(user, 0, 1);
    }

    @org.junit.Test
    public void testBuySeed() {
        User user = userDao.selectById(2);
        shopService.buySeed(user, 0, 1);
    }

    @org.junit.Test
    public void testBuyFood() {
        User user = userDao.selectById(2);
        shopService.buyFood(user, 0, 1);
    }

    @org.junit.Test
    public void testSellFood() {
        User user = userDao.selectById(2);
        shopService.sellGoods(user, 0, 1);
    }

    @org.junit.Test
    public void testGetBabyInfo() {
        List<ShopBabyInfo> babyList = shopService.getBabyInfo();
        Assert.assertEquals(babyList.size(), 2);
    }

    @org.junit.Test
    public void testGetSeedInfo() {
        List<ShopSeedInfo> seedList = shopService.getSeedInfo();
        Assert.assertEquals(seedList.size(), 2);
    }

    @org.junit.Test
    public void testGetFoodInfo() {
        List<ShopFoodInfo> foodList = shopService.getFoodInfo();
        Assert.assertEquals(foodList.size(), 1);
    }

    @org.junit.Test
    public void testGetGoodsInfo() {
        User user = userDao.selectById(2);
        List<ShopGoodsInfo> warehouseList = shopService.getUserGoodsInfo(user);
        Assert.assertEquals(warehouseList.size(), 2);
    }

}
