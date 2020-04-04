package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.BuildLevelinfoDao;
import org.milkteaboy.simplefarm.dao.BuildMaxlevelDao;
import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.dao.UserDao;
import org.milkteaboy.simplefarm.entity.BuildLevelinfo;
import org.milkteaboy.simplefarm.entity.BuildMaxlevel;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.entity.UserBuild;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BuildService实现类
 */
@Service("buildService")
public class BuildServiceImpl implements BuildService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BuildMaxlevelDao buildMaxlevelDao;
    @Autowired
    private BuildLevelinfoDao buildLevelinfoDao;
    @Autowired
    private UserBuildDao userBuildDao;

    @Transactional
    @Override
    public int getUpgradePrice(int userId, int buildId) {
        // 获取用户信息
        User user = userDao.selectById(userId);
        if (user == null)
            throw new BuildException("获取用户信息失败");

        // 判断是否达到当前等级的最大等级
        BuildMaxlevel buildMaxlevel = buildMaxlevelDao.selectByUserLevelAndBuildId(user.getLevel(), buildId);
        if (buildMaxlevel == null)
            throw new BuildException("获取升级信息失败");
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(userId, buildId);
        if (userBuild == null)
            throw new BuildException("获取建筑信息失败");
        if (userBuild.getLevel() >= buildMaxlevel.getMaxLevel())
            throw new BuildException("建筑已到达当前等级的最大等级");

        // 判断当前是否为建筑最大等级
        BuildLevelinfo buildLevelinfo = buildLevelinfoDao.selectByBuildIdAndLevel(buildId, userBuild.getLevel() + 1);
        if (buildLevelinfo == null)
            throw new BuildException("建筑已到达最大等级");

        return buildLevelinfo.getPrice();
    }

    @Transactional
    @Override
    public void upgrade(int userId, int buildId) {
        // 获取用户信息
        User user = userDao.selectById(userId);
        if (user == null)
            throw new BuildException("获取用户信息失败");

        // 判断金币是否足够
        int price = getUpgradePrice(userId, buildId);
        if (user.getCoin() < price)
            throw new BuildException("金币不足");

        // 获取建筑信息
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(userId, buildId);
        if (userBuild == null)
            throw new BuildException("获取建筑信息失败");

        // 升级
        user.setCoin(user.getCoin() - price);
        userBuild.setLevel(userBuild.getLevel() + 1);
        userDao.update(user);
        userBuildDao.update(userBuild);
    }

}
