package org.milkteaboy.simplefarm.dao;

import org.milkteaboy.simplefarm.entity.Seed;

import java.util.List;

/**
 * 种子DAO
 */
public interface SeedDao {

    Seed selectById(Integer id);
    List<Seed> selectAll();
}
