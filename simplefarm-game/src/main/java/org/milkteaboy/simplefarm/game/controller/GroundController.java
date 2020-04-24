package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.GroundService;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.GroundInfo;
import org.milkteaboy.simplefarm.service.dto.GroundReapInfo;
import org.milkteaboy.simplefarm.service.exception.GroundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * ground模块Controller
 */
@Controller("ground")
public class GroundController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private GroundService groundService;

    private static Logger logger = LoggerFactory.getLogger(GroundController.class);

    /**
     * 获取地块信息
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void groundInfo(ChannelHandlerContext ctx, Double groundId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                GroundInfo groundInfo = groundService.getGroundInfo(user, groundId.intValue());
                map.put("success", true);
                map.put("message", "获取成功");
                int state = groundInfo.getState();
                map.put("state", state);
                if (state == 0) {
                    map.put("seedList", groundInfo.getSeedList());
                } else if (state == 1) {
                    map.put("startDateTime", groundInfo.getStartDateTime());
                    map.put("finishDateTime", groundInfo.getFinishDateTime());
                    map.put("waterCount", groundInfo.getWaterCount());
                    map.put("waterMaxCount", groundInfo.getWaterMaxCount());
                } else if (state == 2) {
                    map.put("goodsId", groundInfo.getGoodsId());
                    map.put("count", groundInfo.getCount());
                }
            }
        } catch (GroundException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:ground/groundInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取地块信息失败");
            e.printStackTrace();
            logger.error("method:ground/groundInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "ground/groundInfo", map);
    }

    /**
     * 播种
     * @param ctx 通道
     * @param groundId 地块ID
     * @param seedId 种子ID
     */
    public void sow(ChannelHandlerContext ctx, Double groundId, Double seedId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                groundService.sow(user, groundId.intValue(), seedId.intValue());
                map.put("success", true);
                map.put("message", "播种成功");
            }
        } catch (GroundException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:ground/sow,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "种植失败");
            e.printStackTrace();
            logger.error("method:ground/sow,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "ground/sow", map);
    }

    /**
     * 收获作物
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void reap(ChannelHandlerContext ctx, Double groundId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                GroundReapInfo groundReapInfo = groundService.reap(user, groundId.intValue());
                map.put("success", true);
                map.put("message", "收获成功");
                map.put("id", groundReapInfo.getId());
                map.put("count", groundReapInfo.getCount());
            }
        } catch (GroundException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:ground/reap,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "收获失败");
            e.printStackTrace();
            logger.error("method:ground/reap,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "ground/reap", map);
    }

    /**
     * 浇水
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void water(ChannelHandlerContext ctx, Double groundId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                groundService.water(user, groundId.intValue());
                map.put("success", true);
                map.put("message", "浇水成功");
            }
        } catch (GroundException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:ground/water,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "浇水失败");
            e.printStackTrace();
            logger.error("method:ground/water,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "ground/water", map);
    }

}
