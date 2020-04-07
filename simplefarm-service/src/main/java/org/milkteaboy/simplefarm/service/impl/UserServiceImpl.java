package org.milkteaboy.simplefarm.service.impl;

import org.milkteaboy.simplefarm.dao.UserLevelupDao;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.entity.UserLevelup;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.dto.UserDetailInfo;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.milkteaboy.simplefarm.service.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLevelupDao userLevelupDao;

    @Override
    public UserDetailInfo getUserDetailInfo(User user) {
        if (user == null)
            throw new UserException("用户信息获取失败");

        UserDetailInfo userDetailInfo = new UserDetailInfo();
        userDetailInfo.setUserId(user.getId());
        userDetailInfo.setNickname(user.getNickname());
        userDetailInfo.setLevel(user.getLevel());
        userDetailInfo.setCoin(user.getCoin());
        userDetailInfo.setExp(user.getExp());
        UserLevelup levelup = userLevelupDao.selectByLevel(user.getLevel());
        if (levelup == null)
            throw new UserException("获取等级信息失败");
        userDetailInfo.setNeedExp(levelup.getExp());

        return userDetailInfo;
    }
}
