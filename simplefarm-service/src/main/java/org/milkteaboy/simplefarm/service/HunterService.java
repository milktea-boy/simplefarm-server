package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.HunterInfo;
import org.milkteaboy.simplefarm.service.dto.HunterReapInfo;

/**
 * 猎人小屋Service
 */
public interface HunterService {

    /**
     * 初始化猎人小屋信息
     * @param user 用户
     */
    void initHunterInfo(User user);

    /**
     * 获取猎人小屋信息
     * @param user 用户
     * @return 猎人小屋信息
     */
    HunterInfo getHunterInfo(User user);

    /**
     * 放出猎人
     * @param user 用户
     */
    void sendHunter(User user);

    /**
     * 收回猎人
     * @param user 用户
     * @Return 收获信息
     */
    HunterReapInfo receiveHunter(User user);

}
