package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.*;

import java.util.List;

/**
 * 商店Service
 */
public interface ShopService {

    /**
     * 购买幼崽
     * @param user 用户
     * @param babyId 幼崽ID
     * @param count 数量
     */
    void buyBaby(User user, int babyId, int count);

    /**
     * 购买种子
     * @param user 用户
     * @param seedId 种子ID
     * @param count 数量
     */
    void buySeed(User user, int seedId, int count);

    /**
     * 卖出货物
     * @param user 用户
     * @param goodsId 货物ID
     * @param count 数量
     */
    void sellGoods(User user, int goodsId, int count);

    /**
     * 获取可购买的幼崽信息
     * @return 幼崽列表
     */
    List<Baby> getBabyInfo();

    /**
     * 获取可购买的种子信息
     * @return 种子列表
     */
    List<Seed> getSeedInfo();

    /**
     * 获取可购买的食物信息
     * @return 食物列表
     */
    List<Food> getFoodInfo();

    /**
     * 获取用户货物信息
     * @param user 用户
     * @return 货物列表
     */
    List<Warehouse> getUserGoodsInfo(User user);

}
