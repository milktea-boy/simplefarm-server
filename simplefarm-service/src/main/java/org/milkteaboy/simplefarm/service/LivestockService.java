package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.LivestockInfo;

/**
 * 畜舍Service
 */
public interface LivestockService {

    /**
     * 获取畜舍信息
     * @param user 用户
     * @param livestockId 畜舍ID，0-畜舍1，1-畜舍2
     * @return 畜舍信息
     */
    LivestockInfo getLivestockInfo(User user, int livestockId);

    /**
     * 养殖
     * @param user 用户
     * @param livestockId 畜舍ID，0-畜舍1，1-畜舍2
     * @param babyId 幼崽ID
     * @param count 数量
     */
    void breed(User user, int livestockId, int babyId, int count);

    /**
     * 喂养
     * @param user 用户
     * @param livestockId 畜舍ID，0-畜舍1，1-畜舍2
     */
    void feed(User user, int livestockId);

    /**
     * 收获
     * @param user 用户
     * @param livestockId 畜舍ID，0-畜舍1，1-畜舍2
     */
    void reap(User user, int livestockId);

}
