package org.milkteaboy.simplefarm.game.constant;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态数据类
 */
public class StaticData {

    /**
     * 用户登录信息
     */
    public static Map<ChannelHandlerContext, User> userInfo = new HashMap<ChannelHandlerContext, User>();

}
