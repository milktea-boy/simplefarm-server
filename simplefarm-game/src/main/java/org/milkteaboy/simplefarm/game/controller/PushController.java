package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.dto.UserBuildInfo;
import org.milkteaboy.simplefarm.service.dto.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送控制器
 */
@Controller("pushController")
public class PushController {

    @Autowired
    private SocketServer socketServer;

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(PushController.class);

    /**
     * 用户信息推送
     * @param user
     * @param ctx
     */
    public void updateInfoPush(User user, ChannelHandlerContext ctx) {
        Map<String, Object> userTempInfo = StaticData.userTempInfo.get(user);
        UserInfo userInfo = (UserInfo) userTempInfo.get("farmInfo");
        if (user.getLevel() == userInfo.getLevel() && user.getCoin() == userInfo.getCoin())
            return;

        // 更新临时数据
        userInfo.setLevel(user.getLevel());
        userInfo.setCoin(user.getCoin());

        // 推送消息
        Map<String, Object> map = new HashMap<>();
        map.put("level", userInfo.getLevel());
        map.put("coin", userInfo.getCoin());
        socketServer.sendMessage(ctx, "<userInfo>", map);
    }

    /**
     * 建筑信息推送
     * @param user 用户
     * @param ctx 通道
     */
    public void buildInfoPush(User user, ChannelHandlerContext ctx) {
        Map<String, Object> userTempInfo = StaticData.userTempInfo.get(user);
        UserInfo userInfo = (UserInfo) userTempInfo.get("farmInfo");
        UserInfo newUserInfo = userService.getUserInfo(user);
        for (UserBuildInfo newBuildInfo : newUserInfo.getBuildInfo()) {
            boolean isCheck = false;
            for (UserBuildInfo buildInfo : userInfo.getBuildInfo()) {
                if (buildInfo.getBuildId() != newBuildInfo.getBuildId())
                    continue;

                isCheck = true;
                if (buildInfo.getLevel() == newBuildInfo.getLevel() && buildInfo.getMaxLevel() == newBuildInfo.getMaxLevel())
                    break;

                // 更新信息
                buildInfo.setLevel(newBuildInfo.getLevel());
                buildInfo.setMaxLevel(newBuildInfo.getMaxLevel());

                // 推送消息
                Map<String, Object> map = new HashMap<>();
                map.put("buildId", buildInfo.getBuildId());
                map.put("level", buildInfo.getLevel());
                map.put("maxLevel", buildInfo.getMaxLevel());
                socketServer.sendMessage(ctx, "<buildInfo>", map);

                break;
            }
            if (!isCheck) {
                UserBuildInfo userBuildInfo = new UserBuildInfo();
                userBuildInfo.setBuildId(newBuildInfo.getBuildId());
                userBuildInfo.setLevel(newBuildInfo.getLevel());
                userBuildInfo.setMaxLevel(newBuildInfo.getLevel());
                userInfo.getBuildInfo().add(userBuildInfo);

                // 推送消息
                Map<String, Object> map = new HashMap<>();
                map.put("buildId", userBuildInfo.getBuildId());
                map.put("level", userBuildInfo.getLevel());
                map.put("maxLevel", userBuildInfo.getMaxLevel());
                socketServer.sendMessage(ctx, "<buildInfo>", map);
            }
        }
    }

    /**
     * 初始化地块完成推送
     * @param user 用户
     * @param ctx 通道
     */
    public void initGroundFinishPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 地块信息推送
     * @param user 用户
     * @param ctx 通道
     */
    public void groundInfoPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 初始化畜舍完成推送
     * @param user 用户
     * @param ctx 通道
     */
    public void initLivestockFinishPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 畜舍信息推送
     * @param user 用户
     * @param ctx 通道
     */
    public void livestockInfoPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 初始化水井完成推送
     * @param user 用户
     * @param ctx 通道
     */
    public void initWellFinishPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 水井信息推送
     * @param user 用户
     * @param ctx 通道
     */
    public void wellInfoPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 初始化猎人小屋完成推送
     * @param user 用户
     * @param ctx 通道
     */
    public void initHunterFinishPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 猎人小屋信息推送
     * @param user 用户
     * @param ctx 通道
     */
    public void hunterInfoPush(User user, ChannelHandlerContext ctx) {

    }

    /**
     * 清除所有完成时推送
     * @param user 用户
     */
    public void clearAllFinishPush(User user) {

    }

}
