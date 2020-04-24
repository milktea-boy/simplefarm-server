package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.WarehouseService;
import org.milkteaboy.simplefarm.service.dto.WarehouseBabyInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseFoodInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.WarehouseSeedInfo;
import org.milkteaboy.simplefarm.service.exception.ShopException;
import org.milkteaboy.simplefarm.service.exception.WarehouseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * warehouse模块Controller
 */
@Controller("warehouse")
public class WarehouseController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private BuildService buildService;

    private Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    /**
     * 获取仓库幼崽信息
     * @param ctx 通道
     */
    public void babyInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<WarehouseBabyInfo> babyInfoList = warehouseService.getBabyInfo(user);
                int level = buildService.getBuildLevel(user, 2);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("babyList", babyInfoList);
            }
        } catch (WarehouseException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/babyInf,msg:{}", e.getMessage());
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/babyInf,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取幼崽信息失败");
            e.printStackTrace();
            logger.error("method:warehouse/babyInf,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "warehouse/babyInfo", map);
    }

    /**
     * 获取仓库种子信息
     * @param ctx 通道
     */
    public void seedInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<WarehouseSeedInfo> seedInfoList = warehouseService.getSeedInfo(user);
                int level = buildService.getBuildLevel(user, 2);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("seedList", seedInfoList);
            }
        } catch (WarehouseException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/seedInfo,msg:{}", e.getMessage());
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/seedInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取种子信息失败");
            e.printStackTrace();
            logger.error("method:warehouse/seedInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "warehouse/seedInfo", map);
    }

    /**
     * 获取食物信息
     * @param ctx 通道
     */
    public void foodInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<WarehouseFoodInfo> foodInfoList = warehouseService.getFoodInfo(user);
                int level = buildService.getBuildLevel(user, 2);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("foodList", foodInfoList);
            }
        } catch (WarehouseException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/foodInfo,msg:{}", e.getMessage());
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/foodInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取食物信息失败");
            e.printStackTrace();
            logger.error("method:warehouse/foodInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "warehouse/foodInfo", map);
    }

    /**
     * 获取仓库货物信息
     * @param ctx
     */
    public void goodInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<WarehouseGoodsInfo> goodsInfoList = warehouseService.getGoodsInfo(user);
                int level = buildService.getBuildLevel(user, 2);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("goodsList", goodsInfoList);
            }
        } catch (WarehouseException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/goodInfo,msg:{}", e.getMessage());
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:warehouse/goodInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取货物信息失败");
            e.printStackTrace();
            logger.error("method:warehouse/goodInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "warehouse/goodInfo", map);
    }

}
