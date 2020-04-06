package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.GoodsDao;
import org.milkteaboy.simplefarm.entity.Goods;

import javax.annotation.Resource;
import java.util.List;

public class GoodsTest extends Test {

    @Resource
    private GoodsDao goodsDao;

    @org.junit.Test
    public void testSelect() {
        Goods goods = goodsDao.selectById(0);
        Assert.assertEquals(goods.getPrice().toString(), "10");

        List<Goods> goodsList = goodsDao.selectAll();
        Assert.assertEquals(goodsList.size(), 4);
    }

}
