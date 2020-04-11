package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.dao.UserWellDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.dao.WellLevelinfoDao;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.WellService;
import org.milkteaboy.simplefarm.service.exception.WellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * WellService实现类
 */
@Service("wellService")
public class WellServiceImpl implements WellService {

    @Autowired
    private WellLevelinfoDao wellLevelinfoDao;
    @Autowired
    private UserWellDao userWellDao;
    @Autowired
    private UserBuildDao userBuildDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @Transactional
    @Override
    public void initWellInfo(User user) {
        if (user == null)
            throw new WellException("获取用户信息失败");

        UserWell userWell = userWellDao.selectByUserId(user.getId());
        if (userWell == null) {
            userWell = new UserWell();
            userWell.setUserId(user.getId());
            userWell.setReapDatetime(new Date());

            userWellDao.insert(userWell);
        }
    }

    @Transactional
    @Override
    public int reap(User user) {
        if (user == null)
            throw new WellException("获取用户信息失败");

        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), 7);
        if (userBuild == null)
            throw new WellException("获取水井建筑信息失败");
        if (userBuild.getLevel() <= 0)
            throw new WellException("当前水井未建造");
        WellLevelinfo wellLevelinfo = wellLevelinfoDao.selectByLevel(userBuild.getLevel());
        UserWell userWell = userWellDao.selectByUserId(user.getId());
        if (userWell == null)
            throw new WellException("获取水井信息失败");

        // 计算当前水滴数
        int passSecond = (int)(((new Date()).getTime() - userWell.getReapDatetime().getTime()) / 1000);
        int waterCount = passSecond / wellLevelinfo.getReapInterval() * wellLevelinfo.getReapCount();
        waterCount = waterCount > wellLevelinfo.getMaxCount() ? wellLevelinfo.getMaxCount() : waterCount;

        // 收获操作
        userWell.setReapDatetime(new Date());
        userWellDao.update(userWell);

        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 2, 1);
        if (warehouse == null) {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(2);
            warehouse.setObjectId(1);
            warehouse.setCount(waterCount);

            warehouseDao.insert(warehouse);
        } else {
            warehouse.setCount(warehouse.getCount() + waterCount);

            warehouseDao.update(warehouse);
        }

        return waterCount;
    }
}
