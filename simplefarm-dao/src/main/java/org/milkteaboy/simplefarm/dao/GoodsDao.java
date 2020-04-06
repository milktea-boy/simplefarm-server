package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.Goods;

import java.util.List;

/**
 * 货物DAO
 */
public interface GoodsDao {

    Goods selectById(Integer id);
    List<Goods> selectAll();

}
