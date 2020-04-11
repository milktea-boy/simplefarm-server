package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.entity.UserBuild;
import org.milkteaboy.simplefarm.service.HunterService;
import org.milkteaboy.simplefarm.service.dto.HunterInfo;
import org.milkteaboy.simplefarm.service.exception.HunterException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * HunterService实现类
 */
public class HunterServiceImpl implements HunterService {

    @Autowired
    private UserBuildDao userBuildDao;

    @Override
    public void initHunterInfo(User user) {

    }

    @Override
    public HunterInfo getHunterInfo(User user) {
        return null;
    }

    @Override
    public void sendHunter(User user) {

    }

    @Override
    public void receiveHunter(User user) {

    }

    /**
     * 检查用户和建筑信息
     * @param user 用户
     */
    private void checkUserAndBuildInfo(User user) {
        if (user == null)
            throw new HunterException("用户信息获取失败");

        // 判断建筑是否建造
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), 8);
        if (userBuild == null)
            throw new HunterException("获取建筑信息失败");
        if (userBuild.getLevel() <= 0)
            throw new HunterException("建筑未建造");
    }
}
