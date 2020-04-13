package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.*;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.service.HunterService;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.WellService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.*;
import org.milkteaboy.simplefarm.service.dto.UserGround;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.milkteaboy.simplefarm.service.exception.UserException;
import org.milkteaboy.simplefarm.service.exception.WellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UserService实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLevelupDao userLevelupDao;
    @Autowired
    private UserBuildDao userBuildDao;
    @Autowired
    private BuildMaxlevelDao buildMaxlevelDao;
    @Autowired
    private WellService wellService;
    @Autowired
    private UserLivestockDao userLivestockDao;
    @Autowired
    private UserGroundDao userGroundDao;
    @Autowired
    private HunterService hunterService;

    @Override
    public UserInfo getUserInfo(User user) {
        if (user == null)
            throw new UserException("用户信息获取失败");

        UserInfo userInfo = new UserInfo();
        boolean isWellBuild = false;
        boolean isLivestock0Build = false;
        boolean isLivestock1Build = false;
        boolean isGround0Build = false;
        boolean isGround1Build = false;
        boolean isHunterBuild = false;
        // 农场等级
        userInfo.setLevel(user.getLevel());
        // 金币数
        userInfo.setCoin(user.getCoin());
        // 建筑信息
        List<UserBuild> userBuilds = userBuildDao.selectByUserId(user.getId());
        if (userBuilds == null)
            throw new UserException("获取用户建筑信息失败");
        List<BuildMaxlevel> buildMaxlevels = buildMaxlevelDao.selectByUserLevel(user.getLevel());
        if (buildMaxlevels == null)
            throw new UserException("获取建筑等级信息失败");
        List<UserBuildInfo> userBuildInfos = new ArrayList<>();
        for (int i = 0; i < userBuilds.size(); i++) {
            UserBuildInfo userBuildInfo = new UserBuildInfo();
            userBuildInfo.setBuildId(userBuilds.get(i).getBuildId());
            userBuildInfo.setLevel(userBuilds.get(i).getLevel());
            int maxLevel = 0;
            for (BuildMaxlevel buildMaxlevel : buildMaxlevels) {
                if (buildMaxlevel.getId() == userBuilds.get(i).getBuildId()) {
                    maxLevel = buildMaxlevel.getMaxLevel();
                    break;
                }
            }
            userBuildInfo.setMaxLevel(maxLevel);
            //判断建筑是否建立
            if (userBuildInfo.getBuildId() == Constant.BUILD_ID_WELL && userBuildInfo.getLevel() > 0)
                isWellBuild = true;
            else if (userBuildInfo.getBuildId() == Constant.BUILD_ID_LIVESTOCK1 && userBuildInfo.getLevel() > 0)
                isLivestock0Build = true;
            else if (userBuildInfo.getBuildId() == Constant.BUILD_ID_LIVESTOCK2 && userBuildInfo.getLevel() > 0)
                isLivestock1Build = true;
            else if (userBuildInfo.getBuildId() == Constant.BUILD_ID_GROUND1 && userBuildInfo.getLevel() > 0)
                isGround0Build = true;
            else if (userBuildInfo.getBuildId() == Constant.BUILD_ID_GROUND2 && userBuildInfo.getLevel() > 0)
                isGround1Build = true;
            else if (userBuildInfo.getBuildId() == Constant.BUILD_ID_HUNTER && userBuildInfo.getLevel() > 0)
                isHunterBuild = true;
        }
        userInfo.setBuildInfo(userBuildInfos);
        // 水井
        if (isWellBuild) {
            int waterCount = wellService.getWaterCount(user);
            userInfo.setWellWaterCount(waterCount);
        }
        // 畜舍
        List<UserLivestockInfo> userLivestockInfos = new ArrayList<>();
        if (isLivestock0Build) {
            UserLivestockInfo userLivestockInfo = getUserLivestockInfo(user, Constant.BUILD_ID_LIVESTOCK1);
            userLivestockInfos.add(userLivestockInfo);
        }
        if (isLivestock1Build) {
            UserLivestockInfo userLivestockInfo = getUserLivestockInfo(user, Constant.BUILD_ID_LIVESTOCK2);
            userLivestockInfos.add(userLivestockInfo);
        }
        userInfo.setLivestockInfo(userLivestockInfos);
        // 地块
        List<org.milkteaboy.simplefarm.entity.UserGround> userGrounds = userGroundDao.selectByUserId(user.getId());
        if (userGrounds == null)
            throw new UserException("获取用户地块信息失败");
        List<UserGroundInfo> userGroundInfos = new ArrayList<>();
        if (isGround0Build) {
            UserGroundInfo userGroundInfo = getUserGroundInfo(user, Constant.BUILD_ID_GROUND1, userGrounds);
            userGroundInfos.add(userGroundInfo);
        }
        if (isGround1Build) {
            UserGroundInfo userGroundInfo = getUserGroundInfo(user, Constant.BUILD_ID_GROUND2, userGrounds);
            userGroundInfos.add(userGroundInfo);
        }
        userInfo.setGroundInfo(userGroundInfos);
        // 猎人小屋
        if (isHunterBuild) {
            HunterInfo hunterInfo = hunterService.getHunterInfo(user);
            userInfo.setHunterState(hunterInfo.getState());
        }

        return userInfo;
    }

    private UserLivestockInfo getUserLivestockInfo(User user, int buildId) {
        UserLivestockInfo userLivestockInfo = new UserLivestockInfo();
        userLivestockInfo.setBuildId(3);
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(user.getId(), buildId);
        if (userLivestock == null)
            throw new UserException("获取用户畜舍信息失败");
        userLivestockInfo.setId(userLivestock.getBabyId());
        userLivestockInfo.setCount(userLivestock.getCount());

        return userLivestockInfo;
    }

    private UserGroundInfo getUserGroundInfo(User user, int buildId, List<org.milkteaboy.simplefarm.entity.UserGround> userGrounds) {
        UserGroundInfo userGroundInfo = new UserGroundInfo();
        userGroundInfo.setBuildId(buildId);
        List<UserGround> userGroundList = new ArrayList<>();
        int startIndex = buildId == 5 ? 0 : 6;
        int endIndex = buildId == 5 ? 6 : 12;
        for (int i = startIndex; i < endIndex; i++) {
            for (org.milkteaboy.simplefarm.entity.UserGround userGround : userGrounds) {
                if (userGround.getIndex() == i) {
                    UserGround ground = new UserGround();
                    ground.setIndex(i);
                    if (userGround.getSeed() == null)
                        ground.setId(-1);
                    else {
                        ground.setId(userGround.getSeed().getId());
                        ground.setStartDateTime(userGround.getSowDatetime());
                        Date reapDatetime = new Date(userGround.getSowDatetime().getTime() + userGround.getSeed().getReapInterval() * 1000 - userGround.getWaterCount() * GroundServiceImpl.waterReduceSecond * 1000);
                        ground.setFinishDateTime(reapDatetime);
                    }
                    userGroundList.add(ground);
                    break;
                }
            }
            userGroundInfo.setUserGroundList(userGroundList);
        }

        return userGroundInfo;
    }

    @Override
    public UserDetailInfo getUserDetailInfo(User user) {
        if (user == null)
            throw new UserException("用户信息获取失败");

        UserDetailInfo userDetailInfo = new UserDetailInfo();
        userDetailInfo.setUserId(user.getId());
        userDetailInfo.setNickname(user.getNickname());
        userDetailInfo.setLevel(user.getLevel());
        userDetailInfo.setCoin(user.getCoin());
        userDetailInfo.setExp(user.getExp());
        UserLevelup levelup = userLevelupDao.selectByLevel(user.getLevel());
        if (levelup == null)
            throw new UserException("获取等级信息失败");
        userDetailInfo.setNeedExp(levelup.getExp());

        return userDetailInfo;
    }
}
