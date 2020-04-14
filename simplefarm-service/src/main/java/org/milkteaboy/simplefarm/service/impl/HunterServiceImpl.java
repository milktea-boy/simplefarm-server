package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.HunterLevelinfoDao;
import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.dao.UserHunterDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.HunterService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.HunterInfo;
import org.milkteaboy.simplefarm.service.dto.HunterReapInfo;
import org.milkteaboy.simplefarm.service.exception.HunterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * HunterService实现类
 */
@Service("hunterService")
public class HunterServiceImpl implements HunterService {

    @Autowired
    private UserBuildDao userBuildDao;
    @Autowired
    private UserHunterDao userHunterDao;
    @Autowired
    private HunterLevelinfoDao hunterLevelinfoDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @Transactional
    @Override
    public void initHunterInfo(User user) {
        if (user == null)
            throw new HunterException("用户信息获取失败");

        UserHunter userHunter = userHunterDao.selectByUserId(user.getId());
        if (userHunter == null) {
            userHunter = new UserHunter();
            userHunter.setUserId(user.getId());
            userHunter.setSendDatetime(null);
            userHunter.setGoodsCount(null);

            userHunterDao.insert(userHunter);
        }
    }

    @Override
    public HunterInfo getHunterInfo(User user) {
        checkUserAndBuildInfo(user);

        UserHunter userHunter = userHunterDao.selectByUserId(user.getId());
        if (userHunter == null)
            throw new HunterException("获取猎人小屋信息失败");
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), Constant.BUILD_ID_HUNTER);
        if (userBuild == null)
            throw new HunterException("获取建筑信息失败");
        HunterLevelinfo hunterLevelinfo = hunterLevelinfoDao.selectByLevel(userBuild.getLevel());
        if (hunterLevelinfo == null)
            throw new HunterException("获取建筑等级信息失败");

        HunterInfo hunterInfo = new HunterInfo();
        hunterInfo.setBuildLevel(userBuild.getLevel());
        // 未派出
        if (userHunter.getSendDatetime() == null) {
            hunterInfo.setState(0);
        } else {
            Date receiveDatetime = new Date(userHunter.getSendDatetime().getTime() + hunterLevelinfo.getReceiveInterval() * 1000);
            Date now = new Date();
            // 已派出，不可收获
            if (now.getTime() < receiveDatetime.getTime()) {
                hunterInfo.setState(1);
                hunterInfo.setStartDateTime(userHunter.getSendDatetime());
                hunterInfo.setFinishDateTime(receiveDatetime);
            }
            // 已派出，可收获
            else {
                hunterInfo.setState(2);
                hunterInfo.setGoodsId(Constant.HUNTER_GOODSID);
                hunterInfo.setCount(userHunter.getGoodsCount());
            }
        }

        return hunterInfo;
    }

    @Transactional
    @Override
    public void sendHunter(User user) {
        checkUserAndBuildInfo(user);

        UserHunter userHunter = userHunterDao.selectByUserId(user.getId());
        if (userHunter == null)
            throw new HunterException("获取猎人小屋信息失败");
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), Constant.BUILD_ID_HUNTER);
        if (userBuild == null)
            throw new HunterException("获取建筑信息失败");
        HunterLevelinfo hunterLevelinfo = hunterLevelinfoDao.selectByLevel(userBuild.getLevel());
        if (hunterLevelinfo == null)
            throw new HunterException("获取建筑等级信息失败");

        // 判断是否可派出
        if (userHunter.getSendDatetime() != null)
            throw new HunterException("猎人未处于空闲状态");

        userHunter.setSendDatetime(new Date());
        int receiveCount = hunterLevelinfo.getMinReceiveCount() + (int)(Math.round((hunterLevelinfo.getMaxReceiveCount() - hunterLevelinfo.getMinReceiveCount()) * Math.random()));
        userHunter.setGoodsCount(receiveCount);
        userHunterDao.update(userHunter);
    }

    @Transactional
    @Override
    public HunterReapInfo receiveHunter(User user) {
        checkUserAndBuildInfo(user);

        UserHunter userHunter = userHunterDao.selectByUserId(user.getId());
        if (userHunter == null)
            throw new HunterException("获取猎人小屋信息失败");
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), Constant.BUILD_ID_HUNTER);
        if (userBuild == null)
            throw new HunterException("获取建筑信息失败");
        HunterLevelinfo hunterLevelinfo = hunterLevelinfoDao.selectByLevel(userBuild.getLevel());
        if (hunterLevelinfo == null)
            throw new HunterException("获取建筑等级信息失败");

        // 判断是否可收回
        if (userHunter.getSendDatetime() == null)
            throw new HunterException("猎人未派出");
        Date receiveDatetime = new Date(userHunter.getSendDatetime().getTime() + hunterLevelinfo.getReceiveInterval() * 1000);
        Date now = new Date();
        if (now.getTime() < receiveDatetime.getTime())
            throw new HunterException("未到可收获时间");

        HunterReapInfo hunterReapInfo = new HunterReapInfo();
        hunterReapInfo.setGoodsId(5);
        hunterReapInfo.setCount(userHunter.getGoodsCount());

        // 仓库更新
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), Constant.OBJECT_TYPE_GOODS, 5);
        if (warehouse == null) {
            warehouse = new Warehouse();
            warehouse.setUserId(user.getId());
            warehouse.setObjectType(Constant.OBJECT_TYPE_GOODS);
            warehouse.setObjectId(Constant.HUNTER_GOODSID);
            warehouse.setCount(userHunter.getGoodsCount());

            warehouseDao.insert(warehouse);
        } else {
            warehouse.setCount(warehouse.getCount() + userHunter.getGoodsCount());

            warehouseDao.update(warehouse);
        }

        // 猎人小屋更新
        userHunter.setSendDatetime(null);
        userHunter.setGoodsCount(null);
        userHunterDao.update(userHunter);

        return hunterReapInfo;
    }

    /**
     * 检查用户和建筑信息
     * @param user 用户
     */
    private void checkUserAndBuildInfo(User user) {
        if (user == null)
            throw new HunterException("用户信息获取失败");

        // 判断建筑是否建造
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), Constant.BUILD_ID_HUNTER);
        if (userBuild == null)
            throw new HunterException("获取建筑信息失败");
        if (userBuild.getLevel() <= 0)
            throw new HunterException("建筑未建造");
    }
}
