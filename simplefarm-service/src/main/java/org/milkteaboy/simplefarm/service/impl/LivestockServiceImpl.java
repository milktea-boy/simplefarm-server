package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.LivestockLevelinfoDao;
import org.milkteaboy.simplefarm.dao.UserLivestockDao;
import org.milkteaboy.simplefarm.dao.WarehouseDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.entity.UserLivestock;
import org.milkteaboy.simplefarm.entity.Warehouse;
import org.milkteaboy.simplefarm.service.LivestockService;
import org.milkteaboy.simplefarm.service.dto.LivestockInfo;
import org.milkteaboy.simplefarm.service.exception.LivestockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private int livestockId0 = 3;
    private int livestockId1 = 4;

    @Override
    public LivestockInfo getLivestockInfo(User user, int livestockId) {
        return null;
    }

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

        // 判断幼崽是否足够
        Warehouse warehouse = warehouseDao.selectOne(user.getId(), 0, babyId);
        if (warehouse == null)
            throw new LivestockException("幼崽库存为0，请先购买");
        if (warehouse.getCount() < count)
            throw new LivestockException("幼崽库存不足");

        warehouse.setCount(warehouse.getCount() - count);
        userLivestock.setBabyId(babyId);
        // 未完成
    }

    @Override
    public void feed(User user, int livestockId) {

    }

    @Override
    public void reap(User user, int livestockId) {

    }
}
