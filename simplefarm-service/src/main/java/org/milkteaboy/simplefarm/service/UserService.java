package org.milkteaboy.simplefarm.service;

import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.UserDetailInfo;

/**
 * 用户Service
 */
public interface UserService {

    /**
     * 获取用户详细信息
     * @param user 用户
     * @return 详细信息
     */
    UserDetailInfo getUserDetailInfo(User user);

}
