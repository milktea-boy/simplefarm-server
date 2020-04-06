package org.milkteaboy.simplefarm.dao;

import org.apache.ibatis.annotations.Param;
import org.milkteaboy.simplefarm.entity.Warehouse;

import java.util.List;

/**
 * 仓库DAO
 */
public interface WarehouseDao {

    int insert(Warehouse warehouse);
    int delete(Warehouse warehouse);
    int update(Warehouse warehouse);
    Warehouse selectOne(@Param("userId") Integer userId, @Param("objectType") Integer objectType, @Param("objectId") Integer objectId);
    List<Warehouse> selectBaby(Integer userId);
    List<Warehouse> selectSeed(Integer userId);
    List<Warehouse> selectFood(Integer userId);
    List<Warehouse> selectGoods(Integer userId);

}
