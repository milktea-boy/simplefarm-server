package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.UserDetailInfo;
import org.milkteaboy.simplefarm.service.dto.UserInfo;

/**
 * 用户Service
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param user 用户
     * @return 用户信息
     */
    UserInfo getUserInfo(User user);

    /**
     * 获取用户详细信息
     * @param user 用户
     * @return 详细信息
     */
    UserDetailInfo getUserDetailInfo(User user);

}
