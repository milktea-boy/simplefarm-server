package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;

/**
 * 水井Service
 */
public interface WellService {

    /**
     * 初始化水井信息
     * @param user 用户
     */
    void initWellInfo(User user);

    /**
     * 收获水
     * @param user 用户
     * @return 水滴数
     */
    int reap(User user);

    /**
     * 获取水滴数
     * @param user 用户
     * @return 水滴树
     */
    int getWaterCount(User user);

}
