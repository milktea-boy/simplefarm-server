package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.SeedDao;
import org.milkteaboy.simplefarm.dao.UserGroundDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.Seed;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.entity.UserGround;
import org.milkteaboy.simplefarm.entity.Warehouse;
import org.milkteaboy.simplefarm.service.GroundService;
import org.milkteaboy.simplefarm.service.dto.GroundInfo;
import org.milkteaboy.simplefarm.service.dto.GroundReapInfo;
import org.milkteaboy.simplefarm.service.exception.GroundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * GroundService实现类
 */
public class GroundServiceImpl implements GroundService {

    @Autowired
    private UserGroundDao userGroundDao;
    @Autowired
    private SeedDao seedDao;
    @Autowired
    private WarehouseDao warehouseDao;

    //浇水一次减少秒数
    private int waterReduceSecond = 5;

    @Transactional
    @Override
    public void initGroundInfo(User user) {
        if (user == null)
            throw new GroundException("用户信息获取失败");

        for (int i = 0; i < 12; i++) {
            UserGround userGround = userGroundDao.selectByUserIdAndIndex(user.getId(), i);
            if (userGround == null) {
                userGround = new UserGround();
                userGround.setUserId(user.getId());
                userGround.setIndex(i);
                Seed seed = new Seed();
                seed.setId(-1);
                userGround.setSeed(seed);
                userGround.setSowDatetime(new Date());
                userGround.setWaterCount(0);
                userGround.setGoodsCount(0);

                userGroundDao.insert(userGround);
            }
        }
    }

    @Override
    public GroundInfo getGroundInfo(User user, int groundId) {
        return null;
    }

    @Transactional
    @Override
    public void sow(User user, int groundId, int seedId) {
        if (user == null)
            throw new GroundException("用户信息获取失败");

        // 判断是否可播种
        UserGround userGround = userGroundDao.selectByUserIdAndIndex(user.getId(), groundId);
        if (userGround == null)
            throw new GroundException("获取地块信息失败");
        if (userGround.getSeed() != null)
            throw new GroundException("地块已种植过植物");
        Seed seed = seedDao.selectById(seedId);
        if (seed == null)
            throw new GroundException("无此种子信息");
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 1, seedId);
        if (warehouse == null || warehouse.getCount() <= 0)
            throw new GroundException("种子数量不足");

        // 种植操作
        seed.setId(seedId);
        userGround.setSeed(seed);
        userGround.setSowDatetime(new Date());
        userGround.setWaterCount(0);
        int goodsCount = seed.getMinGoods() + (int)Math.round((seed.getMaxGoods() - seed.getMinGoods()) * Math.random());
        userGround.setGoodsCount(goodsCount);
        userGroundDao.update(userGround);
        warehouse.setCount(warehouse.getCount() - 1);
        if (warehouse.getCount() <= 0) {
            warehouseDao.delete(warehouse);
        } else {
            warehouseDao.update(warehouse);
        }
    }

    @Transactional
    @Override
    public void water(User user, int groundId) {
        if (user == null)
            throw new GroundException("用户信息获取失败");

        // 判断是否可浇水
        UserGround userGround = userGroundDao.selectByUserIdAndIndex(user.getId(), groundId);
        if (userGround == null)
            throw new GroundException("获取地块信息失败");
        if (userGround.getSeed() == null)
            throw new GroundException("此地块未种植植物");
        if (userGround.getWaterCount() >= userGround.getSeed().getMaxWaterCountCount())
            throw new GroundException("当前浇水次数已达上限");
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 2, userGround.getSeed().getFoodId());
        if (warehouse == null)
            throw new GroundException("水库存不足");

        // 浇水操作
        userGround.setWaterCount(userGround.getWaterCount() + 1);
        userGroundDao.update(userGround);
        warehouse.setCount(warehouse.getCount() - 1);
        if (warehouse.getCount() <= 0) {
            warehouseDao.delete(warehouse);
        } else {
            warehouseDao.update(warehouse);
        }
    }

    @Transactional
    @Override
    public GroundReapInfo reap(User user, int groundId) {
        if (user == null)
            throw new GroundException("用户信息获取失败");

        // 判断是否可收获
        UserGround userGround = userGroundDao.selectByUserIdAndIndex(user.getId(), groundId);
        if (userGround == null)
            throw new GroundException("获取地块信息失败");
        if (userGround.getSeed() == null)
            throw new GroundException("此地块未种植植物");
        Date reapDatetime = new Date(userGround.getSowDatetime().getTime() + userGround.getSeed().getReapInterval() * 1000 - userGround.getWaterCount() * waterReduceSecond * 1000);
        Date now = new Date();
        // 收获操作
        if (now.getTime() >= reapDatetime.getTime()) {
            // 仓库增加货物
            Warehouse warehouse = warehouseDao.selectOne(user.getId(), 3, userGround.getSeed().getGoodsId());
            if (warehouse == null) {
                warehouse = new Warehouse();
                warehouse.setUserId(user.getId());
                warehouse.setObjectType(3);
                warehouse.setObjectId(userGround.getSeed().getGoodsId());
                warehouse.setCount(userGround.getGoodsCount());
                warehouseDao.insert(warehouse);
            } else {
                warehouse.setCount(warehouse.getCount() + userGround.getGoodsCount());
                warehouseDao.update(warehouse);
            }

            //重置地块
            userGround.getSeed().setId(-1);
            userGround.setWaterCount(0);
            userGround.setSowDatetime(new Date());
            userGround.setGoodsCount(0);
            userGroundDao.update(userGround);
        } else {
            throw new GroundException("当前未到可收获时间");
        }

        return null;
    }
}
