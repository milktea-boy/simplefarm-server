package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.FoodDao;
import org.milkteaboy.simplefarm.entity.Food;

import javax.annotation.Resource;
import java.util.List;

public class FoodDaoTest extends Test {

    @Resource
    private FoodDao foodDao;

    @org.junit.Test
    public void testSelect() {
        Food food = foodDao.selectById(0);
        Assert.assertEquals(food.getPrice().toString(), "1");

        List<Food> foodList = foodDao.selectAll();
        Assert.assertEquals(foodList.size(), 2);
    }

}
