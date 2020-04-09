package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserGroundDao;
import org.milkteaboy.simplefarm.entity.Seed;
import org.milkteaboy.simplefarm.entity.UserGround;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class UserGroundDaoTest extends Test {

    @Resource
    private UserGroundDao userGroundDao;

    @org.junit.Test
    public void testInsert() {
        UserGround userGround = new UserGround();
        userGround.setUserId(3);
        userGround.setIndex(0);
        Seed seed = new Seed();
        seed.setId(0);
        userGround.setSeed(seed);
        userGround.setWaterCount(0);
        userGround.setSowDatetime(new Date());
        userGround.setGoodsCount(0);

        userGroundDao.insert(userGround);
    }

    @org.junit.Test
    public void testUpdate() {
        UserGround userGround = userGroundDao.selectByUserIdAndIndex(3, 0);
        userGround.setGoodsCount(1);
        userGroundDao.update(userGround);
    }

    @org.junit.Test
    public void testSelect() {
        // UserGround userGround = userGroundDao.selectByUserIdAndIndex(3, 0);
        // Assert.assertEquals(userGround.getWaterCount().toString(), "0");

        List<UserGround> userGroundList = userGroundDao.selectByUserId(3);
        Assert.assertEquals(userGroundList.size(), 0);
    }

}
