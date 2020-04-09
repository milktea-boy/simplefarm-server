package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.BabyDao;
import org.milkteaboy.simplefarm.dao.LivestockLevelinfoDao;
import org.milkteaboy.simplefarm.dao.UserLivestockDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.LivestockService;
import org.milkteaboy.simplefarm.service.WarehouseService;
import org.milkteaboy.simplefarm.service.dto.LivestockInfo;
import org.milkteaboy.simplefarm.service.dto.LivestockReapInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseBabyInfo;
import org.milkteaboy.simplefarm.service.exception.LivestockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LivestockService实现类
 */
@Service("livestockService")
public class LivestockServiceImpl implements LivestockService {

    @Autowired
    private LivestockLevelinfoDao livestockLevelinfoDao;
    @Autowired
    private UserLivestockDao userLivestockDao;
    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private BabyDao babyDao;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private BuildService buildService;

    private int livestockId0 = 3;
    private int livestockId1 = 4;
    //喂养一次减少秒数
    private int feedReduceSecond = 5;

    @Transactional
    @Override
    public void initLivestockInfo(User user) {
        if (user == null)
            throw new LivestockException("用户信息获取失败");

        UserLivestock userLivestock0 = userLivestockDao.selectByUserIdAndBuildId(user.getId(), livestockId0);
        UserLivestock userLivestock1 = userLivestockDao.selectByUserIdAndBuildId(user.getId(), livestockId1);
        if (userLivestock0 == null) {
            userLivestock0 = new UserLivestock();
            userLivestock0.setUserId(user.getId());
            userLivestock0.setBuildId(livestockId0);
            userLivestock0.setBabyId(-1);
            userLivestock0.setCount(0);
            userLivestock0.setBreedDatetime(new Date());
            userLivestock0.setFeedCount(0);
            userLivestock0.setGoodsCount(0);

            userLivestockDao.insert(userLivestock0);
        }
        if (userLivestock1 == null) {
            userLivestock1 = new UserLivestock();
            userLivestock1.setUserId(user.getId());
            userLivestock1.setBuildId(livestockId1);
            userLivestock1.setBabyId(-1);
            userLivestock1.setCount(0);
            userLivestock1.setBreedDatetime(new Date());
            userLivestock1.setFeedCount(0);
            userLivestock1.setGoodsCount(0);

            userLivestockDao.insert(userLivestock1);
        }
    }

    @Override
    public LivestockInfo getLivestockInfo(User user, int livestockId) {
        if (user == null)
            throw new LivestockException("用户信息获取失败");

        // 建筑ID转换
        int buildId = -1;
        if (livestockId == 0)
            buildId = livestockId0;
        if (livestockId == 1)
            buildId = livestockId1;
        if (buildId == -1)
            throw new LivestockException("参数错误");

        LivestockInfo livestockInfo = new LivestockInfo();
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userLivestock == null)
            throw new LivestockException("获取畜舍信息失败");
        int buildLevel = buildService.getBuildLevel(user, buildId);
        livestockInfo.setBuildLevel(buildLevel);

        // 未养殖
        if (userLivestock.getBabyId() == -1) {
            // 设置状态
            livestockInfo.setState(0);
            // 设置可养殖幼崽
            List<WarehouseBabyInfo> warehouseBabyInfoList = warehouseService.getBabyInfo(user);
            livestockInfo.setBabyList(warehouseBabyInfoList);
            // 设置最大人口数
            LivestockLevelinfo livestockLevelinfo = livestockLevelinfoDao.selectByLevel(buildLevel);
            if (livestockLevelinfo == null)
                throw new LivestockException("获取畜舍等级信息失败");
            livestockInfo.setMaxPopuplation(livestockLevelinfo.getMaxPopulation());
        } else {
            Baby baby = babyDao.selectById(userLivestock.getBabyId());
            if (baby == null)
                throw new LivestockException("获取幼崽信息失败");
            Date reapDatetime = new Date(userLivestock.getBreedDatetime().getTime() + baby.getReapInterval() * 1000 - userLivestock.getFeedCount() * feedReduceSecond * 1000);
            Date now = new Date();
            // 可收获
            if (now.getTime() >= reapDatetime.getTime()) {
                // 设置状态
                livestockInfo.setState(2);
                // 设置货物ID
                livestockInfo.setGoodsId(baby.getGoodsId());
                // 设置货物数量
                livestockInfo.setCount(userLivestock.getGoodsCount());
            }
            // 养殖中
            else {
                // 设置状态
                livestockInfo.setState(1);
                // 设置开始时间
                livestockInfo.setStartDateTime(userLivestock.getBreedDatetime());
                // 设置结束时间
                livestockInfo.setFinishDateTime(reapDatetime);
                // 设置当前喂养次数
                livestockInfo.setFeedCount(userLivestock.getFeedCount());
                // 设置最大喂养次数
                livestockInfo.setFeedMaxCount(baby.getMaxFeedCount());
            }
        }

        return livestockInfo;
    }

    @Transactional
    @Override
    public void breed(User user, int livestockId, int babyId, int count) {
        if (count <= 0)
            throw new LivestockException("数量不能小于1");
        if (user == null)
            throw new LivestockException("用户信息获取失败");

        // 建筑ID转换
        int buildId = -1;
        if (livestockId == 0)
            buildId = livestockId0;
        if (livestockId == 1)
            buildId = livestockId1;
        if (buildId == -1)
            throw new LivestockException("参数错误");

        // 判断畜舍是否可养殖
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userLivestock == null)
            throw new LivestockException("获取畜舍信息失败");
        if (userLivestock.getBabyId() != -1)
            throw new LivestockException("该畜舍已养殖过动物");

        // 判断人口
        int buildLevel = buildService.getBuildLevel(user, buildId);
        Baby baby = babyDao.selectById(babyId);
        if (baby == null)
            throw new LivestockException("获取幼崽信息失败");
        LivestockLevelinfo livestockLevelinfo = livestockLevelinfoDao.selectByLevel(buildLevel);
        if (livestockLevelinfo == null)
            throw new LivestockException("获取畜舍等级信息失败");
        if (baby.getPopulation() * count > livestockLevelinfo.getMaxPopulation())
            throw new LivestockException("幼崽人口超过畜舍人口上限");

        // 判断幼崽是否足够
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 0, babyId);
        if (warehouse == null)
            throw new LivestockException("幼崽库存为0，请先购买");
        if (warehouse.getCount() < count)
            throw new LivestockException("幼崽库存不足");

        // 养殖操作
        warehouse.setCount(warehouse.getCount() - count);
        userLivestock.setBabyId(babyId);
        userLivestock.setCount(count);
        userLivestock.setFeedCount(0);
        userLivestock.setBreedDatetime(new Date());
        // 随机货物数量
        int goodsCount = baby.getMinGoods() + (int)Math.round(Math.random() * (baby.getMaxGoods() - baby.getMinGoods()));
        goodsCount = goodsCount * count;
        userLivestock.setGoodsCount(goodsCount);

        warehouseDao.update(warehouse);
        userLivestockDao.update(userLivestock);
    }

    @Transactional
    @Override
    public void feed(User user, int livestockId) {
        if (user == null)
            throw new LivestockException("用户信息获取失败");

        // 建筑ID转换
        int buildId = -1;
        if (livestockId == 0)
            buildId = livestockId0;
        if (livestockId == 1)
            buildId = livestockId1;
        if (buildId == -1)
            throw new LivestockException("参数错误");

        // 判断畜舍是否可喂养
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userLivestock == null)
            throw new LivestockException("获取畜舍信息失败");
        if (userLivestock.getBabyId() == -1)
            throw new LivestockException("该畜舍未进行养殖");
        Baby baby = babyDao.selectById(userLivestock.getBabyId());
        if (baby == null)
            throw new LivestockException("获取幼崽信息失败");
        Date reapDatetime = new Date(userLivestock.getBreedDatetime().getTime() + baby.getReapInterval() * 1000 - userLivestock.getFeedCount() * feedReduceSecond * 1000);
        Date now = new Date();
        if (now.getTime() > reapDatetime.getTime())
            throw new LivestockException("幼崽已成熟，不可喂养");
        if (userLivestock.getFeedCount() >= baby.getMaxFeedCount())
            throw new LivestockException("喂养次数已达上限");
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 2, baby.getFoodId());
        if (warehouse == null || warehouse.getCount() < 1)
            throw new LivestockException("食物库存不足");

        // 喂养操作
        userLivestock.setFeedCount(userLivestock.getFeedCount() + 1);
        warehouse.setCount(warehouse.getCount() - 1);
        userLivestockDao.update(userLivestock);
        if (warehouse.getCount() <= 0) {
            warehouseDao.delete(warehouse);
        } else {
            warehouseDao.update(warehouse);
        }
    }

    @Override
    public LivestockReapInfo reap(User user, int livestockId) {
        if (user == null)
            throw new LivestockException("用户信息获取失败");

        // 建筑ID转换
        int buildId = -1;
        if (livestockId == 0)
            buildId = livestockId0;
        if (livestockId == 1)
            buildId = livestockId1;
        if (buildId == -1)
            throw new LivestockException("参数错误");

        // 判断畜舍是否可收获
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userLivestock == null)
            throw new LivestockException("获取畜舍信息失败");
        if (userLivestock.getBabyId() == -1)
            throw new LivestockException("该畜舍未进行养殖");
        Baby baby = babyDao.selectById(userLivestock.getBabyId());
        if (baby == null)
            throw new LivestockException("获取幼崽信息失败");
        Date reapDatetime = new Date(userLivestock.getBreedDatetime().getTime() + baby.getReapInterval() * 1000 - userLivestock.getFeedCount() * feedReduceSecond * 1000);
        Date now = new Date();

        // 收获操作
        if (now.getTime() >= reapDatetime.getTime()) {
            LivestockReapInfo livestockReapInfo = new LivestockReapInfo();
            livestockReapInfo.setId(baby.getId());
            livestockReapInfo.setCount(userLivestock.getGoodsCount());

            // 仓库增加货物
            Warehouse warehouse = warehouseDao.selectOne(user.getId(), 3, baby.getGoodsId());
            if (warehouse == null) {
                warehouse = new Warehouse();
                warehouse.setUserId(user.getId());
                warehouse.setObjectType(3);
                warehouse.setObjectId(baby.getGoodsId());
                warehouse.setCount(userLivestock.getGoodsCount());

                warehouseDao.insert(warehouse);
            } else {
                warehouse.setCount(warehouse.getCount() + userLivestock.getGoodsCount());

                warehouseDao.update(warehouse);
            }

            // 畜舍信息重置
            userLivestock.setBabyId(-1);
            userLivestock.setCount(0);
            userLivestock.setFeedCount(0);
            userLivestock.setBreedDatetime(new Date());
            userLivestock.setGoodsCount(0);

            userLivestockDao.update(userLivestock);

            return livestockReapInfo;
        } else {
            throw new LivestockException("未到可收获时间");
        }
    }
}
