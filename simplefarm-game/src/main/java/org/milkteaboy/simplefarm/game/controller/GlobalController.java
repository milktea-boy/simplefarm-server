package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * global模块Controller
 */
@Controller("global")
public class GlobalController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private BuildService buildService;

    /**
     * 获取农场信息
     * @param ctx 通道
     */
    public void farmInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 获取农场详细信息
     * @param ctx 通道
     */
    public void farmDetailInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 获取建筑升级信息
     * @param ctx 通道
     * @param buildId 建筑ID
     */
    public void upgradeBuildInfo(ChannelHandlerContext ctx, Double buildId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                int price = buildService.getUpgradePrice(user, buildId.intValue());
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("price", price);
            }
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取升级信息失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "global/upgradeBuildInfo", map);
    }

    /**
     * 升级建筑
     * @param ctx 通道
     * @param buildId 建筑ID
     */
    public void upgradeBuild(ChannelHandlerContext ctx, Double buildId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                buildService.upgrade(user, buildId.intValue());
                map.put("success", true);
                map.put("message", "升级成功");
            }
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "升级失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "global/upgradeBuild", map);
    }

}
