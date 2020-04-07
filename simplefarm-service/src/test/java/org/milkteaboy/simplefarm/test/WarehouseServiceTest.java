package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.WarehouseService;
import org.milkteaboy.simplefarm.service.dto.WarehouseBabyInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseFoodInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseSeedInfo;

import javax.annotation.Resource;
import java.util.List;

public class WarehouseServiceTest extends Test {

    @Resource
    private UserDao userDao;
    @Resource
    private WarehouseService warehouseService;

    @org.junit.Test
    public void testGetBabyInfo() {
        User user = userDao.selectById(2);
        List<WarehouseBabyInfo> babyInfoList = warehouseService.getBabyInfo(user);
        Assert.assertEquals(babyInfoList.size(), 1);
    }

    @org.junit.Test
    public void testGetSeedInfo() {
        User user = userDao.selectById(2);
        List<WarehouseSeedInfo> seedInfoList = warehouseService.getSeedInfo(user);
        Assert.assertEquals(seedInfoList.size(), 1);
    }

    @org.junit.Test
    public void testGetFoodInfo() {
        User user = userDao.selectById(2);
        List<WarehouseFoodInfo> foodInfoList = warehouseService.getFoodInfo(user);
        Assert.assertEquals(foodInfoList.size(), 1);
    }

    @org.junit.Test
    public void testGetGoodsInfo() {
        User user = userDao.selectById(2);
        List<WarehouseGoodsInfo> goodsInfoList = warehouseService.getGoodsInfo(user);
        Assert.assertEquals(goodsInfoList.size(), 2);
    }

}
