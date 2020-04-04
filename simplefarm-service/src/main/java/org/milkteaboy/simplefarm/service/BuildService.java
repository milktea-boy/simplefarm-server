package org.milkteaboy.simplefarm.service;

/**
 * 建筑Service
 */
public interface BuildService {

    /**
     * 获取升级价格
     * @param userId 用户ID
     * @param buildId 建筑ID
     * @return 价格
     */
    int getUpgradePrice(int userId, int buildId);

    /**
     * 升级建筑
     * @param userId 用户ID
     * @param buildId 建筑ID
     */
    void upgrade(int userId, int buildId);

}
