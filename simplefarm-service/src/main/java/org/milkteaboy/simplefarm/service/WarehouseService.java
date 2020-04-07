package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.WarehouseBabyInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseFoodInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseSeedInfo;

import java.util.List;

/**
 * 仓库Service
 */
public interface WarehouseService {

    /**
     * 获取仓库幼崽信息
     * @param user 用户
     * @return 幼崽列表
     */
    List<WarehouseBabyInfo> getBabyInfo(User user);

    /**
     * 获取仓库种子信息
     * @param user 用户
     * @return 种子列表
     */
    List<WarehouseSeedInfo> getSeedInfo(User user);

    /**
     * 获取仓库食物信息
     * @param user 用户
     * @return 食物列表
     */
    List<WarehouseFoodInfo> getFoodInfo(User user);

    /**
     * 获取仓库货物信息
     * @param user 用户
     * @return 货物列表
     */
    List<WarehouseGoodsInfo> getGoodsInfo(User user);

}
