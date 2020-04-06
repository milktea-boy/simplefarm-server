package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.Baby;

import java.util.List;

/**
 * 幼崽DAO
 */
public interface BabyDao {

    Baby selectById(Integer id);
    List<Baby> selectAll();

}
