package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.Food;

import java.util.List;

/**
 * 食物DAO
 */
public interface FoodDao {

    Food selectById(Integer id);
    List<Food> selectAll();

}
