package org.milkteaboy.simplefarm.game.constant;

import io.netty.channel.Channel;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.service.dto.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态数据类
 */
public class StaticData {

    /**
     * 用户登录信息
     */
    public static Map<Channel, User> userInfo = new HashMap<Channel, User>();

}
