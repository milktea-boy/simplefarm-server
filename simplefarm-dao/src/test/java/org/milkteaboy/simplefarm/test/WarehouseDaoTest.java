package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.Warehouse;

import javax.annotation.Resource;
import java.util.List;

public class WarehouseDaoTest extends Test {

    @Resource
    private WarehouseDao warehouseDao;

    @org.junit.Test
    public void testInsert() {
        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(2);
        warehouse.setObjectType(0);
        warehouse.setObjectId(0);
        warehouse.setCount(2);

        int result = warehouseDao.insert(warehouse);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testDelete() {
        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(2);
        warehouse.setObjectType(0);
        warehouse.setObjectId(0);
        warehouse.setCount(2);

        int result = warehouseDao.delete(warehouse);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testUpdate() {
        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(2);
        warehouse.setObjectType(0);
        warehouse.setObjectId(0);
        warehouse.setCount(4);

        int result = warehouseDao.update(warehouse);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        List<Warehouse> babyList = warehouseDao.selectBaby(2);
        Assert.assertEquals(babyList.size(), 1);

        List<Warehouse> seedList = warehouseDao.selectSeed(2);
        Assert.assertEquals(seedList.size(), 1);

        List<Warehouse> foodList = warehouseDao.selectFood(2);
        Assert.assertEquals(foodList.size(), 1);

        List<Warehouse> goodList = warehouseDao.selectGoods(2);
        Assert.assertEquals(goodList.size(), 1);
    }

}
