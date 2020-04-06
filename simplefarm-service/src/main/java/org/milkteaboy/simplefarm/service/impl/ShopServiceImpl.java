package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.*;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.ShopService;
import org.milkteaboy.simplefarm.service.exception.ShopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ShopService实现类
 */
public class ShopServiceImpl implements ShopService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BabyDao babyDao;
    @Autowired
    private SeedDao seedDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @Transactional
    @Override
    public void buyBaby(User user, int babyId, int count) {
        if (user == null)
            throw new ShopException("用户信息获取失败");

        // 获取幼崽信息
        Baby baby = babyDao.selectById(babyId);
        if (baby == null)
            throw new ShopException("幼崽ID错误");
        // 判断金币是否足够
        int price = baby.getPrice() * count;
        if (user.getCoin() < price)
            throw new ShopException("金币不足");

        // 购买操作
        user.setCoin(user.getCoin() - price);
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 0, babyId);
        if (warehouse != null) {
            warehouse.setCount(warehouse.getCount() + count);

            warehouseDao.update(warehouse);
            userDao.update(user);
        } else {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(0);
            warehouse.setObjectId(babyId);
            warehouse.setCount(count);

            warehouseDao.insert(warehouse);
            userDao.update(user);
        }
    }

    @Transactional
    @Override
    public void buySeed(User user, int seedId, int count) {
        if (user == null)
            throw new ShopException("用户信息获取失败");

        // 获取种子信息
        Seed seed = seedDao.selectById(seedId);
        if (seed == null)
            throw new ShopException("种子ID错误");
        // 判断金币是否足够
        int price = seed.getPrice() * count;
        if (user.getCoin() < price)
            throw new ShopException("金币不足");

        // 购买操作
        user.setCoin(user.getCoin() - price);
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 1, seedId);
        if (warehouse != null) {
            warehouse.setCount(warehouse.getCount() + count);

            warehouseDao.update(warehouse);
            userDao.update(user);
        } else {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(1);
            warehouse.setObjectId(seedId);
            warehouse.setCount(count);

            warehouseDao.insert(warehouse);
            userDao.update(user);
        }
    }

    @Transactional
    @Override
    public void sellGoods(User user, int goodsId, int count) {
        if (user == null)
            throw new ShopException("用户信息获取失败");

        // 获取可售出货物信息
        List<Warehouse> warehouseList = warehouseDao.selectGoods(user.getId());
        Warehouse sellGoods = null;
        for (Warehouse warehouse : warehouseList) {
            if (warehouse.getObjectId() == goodsId) {
                sellGoods = warehouse;
                break;
            }
        }
        if (sellGoods == null)
            throw new ShopException("无此可售出货物");
        if (sellGoods.getCount() < count)
            throw new ShopException("可售出数量不足");

        // 获取货物信息
        Goods goods = goodsDao.selectById(goodsId);
        if (goods == null)
            throw new ShopException("获取货物信息失败");

        // 售出操作
        int price = goods.getPrice() * count;
        user.setCoin(user.getCoin() + price);
        sellGoods.setCount(sellGoods.getCount() - count);
        userDao.update(user);
        if (sellGoods.getCount() > 0) {
            warehouseDao.update(sellGoods);
        } else {
            warehouseDao.delete(sellGoods);
        }
    }

    @Override
    public List<Baby> getBabyInfo() {
        List<Baby> babyList = babyDao.selectAll();

        return babyList;
    }

    @Override
    public List<Seed> getSeedInfo() {
        List<Seed> seedList = seedDao.selectAll();

        return seedList;
    }

    @Override
    public List<Food> getFoodInfo() {
        List<Food> foodList = foodDao.selectAll();
        // 排除水
        for (Food food : foodList) {
            if (food.getId() == 1) {
                foodList.remove(food);
                break;
            }
        }

        return foodList;
    }

    @Override
    public List<Warehouse> getUserGoodsInfo(User user) {
        List<Warehouse> warehouseList = warehouseDao.selectGoods(user.getId());

        return warehouseList;
    }

}
