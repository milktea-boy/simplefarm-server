package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.GroundInfo;
import org.milkteaboy.simplefarm.service.dto.GroundReapInfo;

/**
 * 地块Service
 */
public interface GroundService {

    /**
     * 初始化地块信息
     * @param user 用户
     */
    void initGroundInfo(User user);

    /**
     * 获取地块信息
     * @param user 用户
     * @param groundId 地块ID
     * @return 地块信息
     */
    GroundInfo getGroundInfo(User user, int groundId);

    /**
     * 播种
     * @param user 用户
     * @param groundId 地块ID
     * @param seedId 种子ID
     */
    void sow(User user, int groundId, int seedId);

    /**
     * 浇水
     * @param user 用户
     * @param groundId 地块ID
     */
    void water(User user, int groundId);

    /**
     * 收获
     * @param user 用户
     * @param groundId 地块ID
     * @return 收获信息
     */
    GroundReapInfo reap(User user, int groundId);

}
