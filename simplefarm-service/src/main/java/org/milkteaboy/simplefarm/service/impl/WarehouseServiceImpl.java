package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.BabyDao;
import org.milkteaboy.simplefarm.dao.GoodsDao;
import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.WarehouseService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.WarehouseBabyInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseFoodInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseSeedInfo;
import org.milkteaboy.simplefarm.service.exception.WarehouseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * WarehouseService实现类
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private UserBuildDao userBuildDao;
    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private BabyDao babyDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<WarehouseBabyInfo> getBabyInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Warehouse> warehouseList = warehouseDao.selectBaby(user.getId());
        List<WarehouseBabyInfo> babyInfoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            WarehouseBabyInfo warehouseBabyInfo = new WarehouseBabyInfo();
            warehouseBabyInfo.setId(warehouse.getObjectId());
            warehouseBabyInfo.setCount(warehouse.getCount());
            Baby baby = babyDao.selectById(warehouse.getObjectId());
            if (baby == null)
                throw new WarehouseException("获取幼崽信息失败");
            warehouseBabyInfo.setPopulation(baby.getPopulation());

            babyInfoList.add(warehouseBabyInfo);
        }

        return babyInfoList;
    }

    @Override
    public List<WarehouseSeedInfo> getSeedInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Warehouse> warehouseList = warehouseDao.selectSeed(user.getId());
        List<WarehouseSeedInfo> seedInfoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            WarehouseSeedInfo warehouseSeedInfo = new WarehouseSeedInfo();
            warehouseSeedInfo.setId(warehouse.getObjectId());
            warehouseSeedInfo.setCount(warehouse.getCount());

            seedInfoList.add(warehouseSeedInfo);
        }

        return seedInfoList;
    }

    @Override
    public List<WarehouseFoodInfo> getFoodInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Warehouse> warehouseList = warehouseDao.selectFood(user.getId());
        List<WarehouseFoodInfo> foodInfoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            WarehouseFoodInfo warehouseFoodInfo = new WarehouseFoodInfo();
            warehouseFoodInfo.setId(warehouse.getObjectId());
            warehouseFoodInfo.setCount(warehouse.getCount());

            foodInfoList.add(warehouseFoodInfo);
        }

        return foodInfoList;
    }

    @Override
    public List<WarehouseGoodsInfo> getGoodsInfo(User user) {
        checkUserAndBuildInfo(user);

        List<Warehouse> warehouseList = warehouseDao.selectGoods(user.getId());
        List<WarehouseGoodsInfo> goodsInfoList = new ArrayList<>();
        for (Warehouse warehouse : warehouseList) {
            WarehouseGoodsInfo warehouseGoodsInfo = new WarehouseGoodsInfo();
            warehouseGoodsInfo.setId(warehouse.getObjectId());
            warehouseGoodsInfo.setCount(warehouse.getCount());
            Goods goods = goodsDao.selectById(warehouse.getObjectId());
            if (goods == null)
                throw new WarehouseException("获取货物信息失败");
            warehouseGoodsInfo.setPrice(goods.getPrice());

            goodsInfoList.add(warehouseGoodsInfo);
        }

        return goodsInfoList;
    }

    /**
     * 检查用户和建筑信息
     * @param user 用户
     */
    private void checkUserAndBuildInfo(User user) {
        if (user == null)
            throw new WarehouseException("用户信息获取失败");

        // 判断建筑是否建造
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), Constant.BUILD_ID_WAREHOUSE);
        if (userBuild == null)
            throw new WarehouseException("获取建筑信息失败");
        if (userBuild.getLevel() <= 0)
            throw new WarehouseException("仓库未建造");
    }
}
