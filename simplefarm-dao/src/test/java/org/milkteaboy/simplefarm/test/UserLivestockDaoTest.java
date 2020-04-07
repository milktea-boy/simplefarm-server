package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserLivestockDao;
import org.milkteaboy.simplefarm.entity.UserLivestock;

import javax.annotation.Resource;
import java.util.Date;

public class UserLivestockDaoTest extends Test {

    @Resource
    private UserLivestockDao userLivestockDao;

    @org.junit.Test
    public void testInsert() {
        UserLivestock userLivestock = new UserLivestock();
        userLivestock.setUserId(2);
        userLivestock.setBuildId(3);
        userLivestock.setBabyId(-1);
        userLivestock.setCount(0);
        userLivestock.setFeedCount(0);
        userLivestock.setBreedDatetime(new Date());
        userLivestock.setGoodsCount(0);
        int result = userLivestockDao.insert(userLivestock);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testUpdate() {
        UserLivestock userLivestock = new UserLivestock();
        userLivestock.setUserId(2);
        userLivestock.setBuildId(3);
        userLivestock.setBabyId(-1);
        userLivestock.setCount(0);
        userLivestock.setFeedCount(0);
        userLivestock.setBreedDatetime(new Date());
        userLivestock.setGoodsCount(0);
        int result = userLivestockDao.update(userLivestock);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        UserLivestock userLivestock = userLivestockDao.selectByUserIdAndBuildId(2, 3);
        Assert.assertEquals(userLivestock.getCount().toString(), "0");
    }

}
