package org.milkteaboy.simplefarm.test;

import org.junit.Assert;
import org.milkteaboy.simplefarm.dao.UserBuildDao;
import org.milkteaboy.simplefarm.entity.UserBuild;

import javax.annotation.Resource;
import java.util.List;

public class UserBuildDaoTest extends Test {

    @Resource
    private UserBuildDao userBuildDao;

    @org.junit.Test
    public void testInsert() {
        UserBuild userBuild = new UserBuild();
        userBuild.setUserId(1);
        userBuild.setBuildId(0);
        userBuild.setLevel(1);
        int result = userBuildDao.insert(userBuild);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testUpdate() {
        UserBuild userBuild = userBuildDao.selectByUserIdAndBuildId(1, 0);
        userBuild.setLevel(2);
        int result = userBuildDao.update(userBuild);
        Assert.assertEquals(result, 1);
    }

    @org.junit.Test
    public void testSelect() {
        List<UserBuild> userBuildList = userBuildDao.selectByUserId(1);
        Assert.assertEquals(userBuildList.size(), 1);
    }

}
