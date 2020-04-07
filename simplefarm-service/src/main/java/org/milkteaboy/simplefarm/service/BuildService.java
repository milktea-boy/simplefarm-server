package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;

/**
 * 建筑Service
 */
public interface BuildService {

    /**
     * 初始化用户建筑信息，在用户新注册时调用
     * @param user 用户
     */
    void initBuildInfo(User user);

    /**
     * 获取建筑等级
     * @param user 用户
     * @param buildId 建筑ID
     * @return 等级
     */
    int getBuildLevel(User user, int buildId);

    /**
     * 获取升级价格
     * @param user 用户
     * @param buildId 建筑ID
     * @return 价格
     */
    int getUpgradePrice(User user, int buildId);

    /**
     * 升级建筑
     * @param user 用户
     * @param buildId 建筑ID
     */
    void upgrade(User user, int buildId);

}
