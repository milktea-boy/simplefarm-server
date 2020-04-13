package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.*;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.ShopService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.ShopBabyInfo;
import org.milkteaboy.simplefarm.service.dto.ShopFoodInfo;
import org.milkteaboy.simplefarm.service.dto.ShopGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.ShopSeedInfo;
import org.milkteaboy.simplefarm.service.exception.ShopException;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ShopService实现类
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserBuildDao userBuildDao;
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
        checkUserAndBuildInfo(user);
        if (count <= 0)
            throw new ShopException("数量不能小于1");

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
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), Constant.OBJECT_TYPE_BABY, babyId);
        if (warehouse != null) {
            warehouse.setCount(warehouse.getCount() + count);

            warehouseDao.update(warehouse);
            userDao.update(user);
        } else {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(Constant.OBJECT_TYPE_BABY);
            warehouse.setObjectId(babyId);
            warehouse.setCount(count);

            warehouseDao.insert(warehouse);
            userDao.update(user);
        }
    }

    @Transactional
    @Override
    public void buySeed(User user, int seedId, int count) {
        checkUserAndBuildInfo(user);
        if (count <= 0)
            throw new ShopException("数量不能小于1");

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
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), Constant.OBJECT_TYPE_SEED, seedId);
        if (warehouse != null) {
            warehouse.setCount(warehouse.getCount() + count);

            warehouseDao.update(warehouse);
            userDao.update(user);
        } else {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(Constant.OBJECT_TYPE_SEED);
            warehouse.setObjectId(seedId);
            warehouse.setCount(count);

            warehouseDao.insert(warehouse);
            userDao.update(user);
        }
    }

    @Transactional
    @Override
    public void buyFood(User user, int foodId, int count) {
        checkUserAndBuildInfo(user);
        if (count <= 0)
            throw new ShopException("数量不能小于1");
        // 过滤水
        if (foodId == 1)
            throw new ShopException("水不可购买");

        // 获取食物信息
        Food food = foodDao.selectById(foodId);
        if (food == null)
            throw new ShopException("食物ID错误");
        // 判断金币是否足够
        int price = food.getPrice() * count;
        if (user.getCoin() < price)
            throw new ShopException("金币不足");

        // 购买操作
        user.setCoin(user.getCoin() - price);
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), Constant.OBJECT_TYPE_FOOD, foodId);
        if (warehouse != null) {
            warehouse.setCount(warehouse.getCount() + count);

            warehouseDao.update(warehouse);
            userDao.update(user);
        } else {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(Constant.OBJECT_TYPE_FOOD);
            warehouse.setObjectId(foodId);
            warehouse.setCount(count);

            warehouseDao.insert(warehouse);
            userDao.update(user);
        }
    }

    @Transactional
    @Override
    public void sellGoods(User user, int goodsId, int count) {
        checkUserAndBuildInfo(user);
        if (count <= 0)
            throw new ShopException("数量不能小于1");

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
    public List<ShopBabyInfo> getBabyInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Baby> babyList = babyDao.selectAll();
        List<ShopBabyInfo> babyOutputList = new ArrayList<>();
        for (Baby baby : babyList) {
            ShopBabyInfo shopBabyInfo = new ShopBabyInfo();
            shopBabyInfo.setId(baby.getId());
            shopBabyInfo.setPrice(baby.getPrice());
        }

        return babyOutputList;
    }

    @Override
    public List<ShopSeedInfo> getSeedInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Seed> seedList = seedDao.selectAll();
        List<ShopSeedInfo> seedOutputList = new ArrayList<>();
        for (Seed seed : seedList) {
            ShopSeedInfo shopSeedInfo = new ShopSeedInfo();
            shopSeedInfo.setId(seed.getId());
            shopSeedInfo.setPrice(seed.getPrice());
        }

        return seedOutputList;
    }

    @Override
    public List<ShopFoodInfo> getFoodInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Food> foodList = foodDao.selectAll();
        // 排除水
        for (Food food : foodList) {
            if (food.getId() == 1) {
                foodList.remove(food);
                break;
            }
        }
        List<ShopFoodInfo> foodOutputList = new ArrayList<>();
        for (Food food : foodList) {
            ShopFoodInfo shopFoodInfo = new ShopFoodInfo();
            shopFoodInfo.setId(food.getId());
            shopFoodInfo.setPrice(food.getPrice());
        }

        return foodOutputList;
    }

    @Override
    public List<ShopGoodsInfo> getUserGoodsInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Warehouse> warehouseList = warehouseDao.selectGoods(user.getId());
        List<ShopGoodsInfo> shopGoodsInfos = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
            shopGoodsInfo.setId(warehouse.getObjectId());
            shopGoodsInfo.setCount(warehouse.getCount());
            Goods goods = goodsDao.selectById(warehouse.getObjectId());
            if (goods == null)
                throw new ShopException("货物货物信息失败");
            shopGoodsInfo.setPrice(goods.getPrice());
        }

        return shopGoodsInfos;
    }

    /**
     * 检查用户信息和建筑
     * @param user 用户
     */
    private void checkUserAndBuildInfo(User user) {
        if (user == null)
            throw new ShopException("用户信息获取失败");

        // 判断建筑是否建造
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), 1);
        if (userBuild == null)
            throw new ShopException("获取建筑信息失败");
        if (userBuild.getLevel() <= 0)
            throw new ShopException("建筑未建造");
    }

}
