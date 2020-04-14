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
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void initBuildInfo(User user) {
        if (user == null)
            throw new BuildException("用户信息获取失败");

        List<UserBuild> userBuildList = userBuildDao.selectByUserId(user.getId());
        // 判断是否存在建筑记录
        UserBuild[] userBuilds = new UserBuild[9];
        for (UserBuild userBuild : userBuildList) {
            userBuilds[userBuild.getBuildId()] = userBuild;
        }
        for (int i = 0; i < 9; i++) {
            // 无此记录
            if (userBuilds[i] == null) {
                // 插入记录
                UserBuild userBuild = new UserBuild();
                userBuild.setUserId(user.getId());
                userBuild.setBuildId(i);
                // 家默认1级，其他默认0级
                if (i == Constant.BUILD_ID_HOME)
                    userBuild.setLevel(1);
                else
                    userBuild.setLevel(0);
                userBuildDao.insert(userBuild);
            }
        }
    }

    @Override
    public int getBuildLevel(User user, int buildId) {
        if (user == null)
            throw new BuildException("用户信息获取失败");

        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userBuild == null)
            throw new BuildException("获取建筑信息失败");

        return userBuild.getLevel();
    }

    @Transactional
    @Override
    public int getUpgradePrice(User user, int buildId) {
        if (user == null)
            throw new BuildException("用户信息获取失败");

        // 判断是否达到当前等级的最大等级
        BuildMaxlevel buildMaxlevel = buildMaxlevelDao.selectByUserLevelAndBuildId(user.getLevel(), buildId);
        if (buildMaxlevel == null)
            throw new BuildException("获取升级信息失败");
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), buildId);
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
    public void upgrade(User user, int buildId) {
        if (user == null)
            throw new BuildException("用户信息获取失败");

        // 判断金币是否足够
        int price = getUpgradePrice(user, buildId);
        if (user.getCoin() < price)
            throw new BuildException("金币不足");

        // 获取建筑信息
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userBuild == null)
            throw new BuildException("获取建筑信息失败");

        // 升级
        user.setCoin(user.getCoin() - price);
        userBuild.setLevel(userBuild.getLevel() + 1);
        userDao.update(user);
        userBuildDao.update(userBuild);
    }

}
