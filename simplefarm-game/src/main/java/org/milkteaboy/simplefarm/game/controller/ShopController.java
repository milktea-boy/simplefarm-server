package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.*;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.BuildService;
import org.milkteaboy.simplefarm.service.ShopService;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.ShopBabyInfo;
import org.milkteaboy.simplefarm.service.dto.ShopFoodInfo;
import org.milkteaboy.simplefarm.service.dto.ShopGoodsInfo;
import org.milkteaboy.simplefarm.service.dto.ShopSeedInfo;
import org.milkteaboy.simplefarm.service.exception.BuildException;
import org.milkteaboy.simplefarm.service.exception.ShopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * shop模块Controller
 */
@Controller("shop")
public class ShopController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private ShopService shopService;
    @Autowired
    private BuildService buildService;

    private Logger logger = LoggerFactory.getLogger(ShopController.class);

    /**
     * 获取商店幼崽信息
     * @param ctx 通道
     */
    public void babyInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<ShopBabyInfo> babyList = shopService.getBabyInfo(user);
                int level = buildService.getBuildLevel(user, 1);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("babyList", babyList);
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/babyInfo,msg:{}", e.getMessage());
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/babyInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取幼崽信息失败");
            e.printStackTrace();
            logger.error("method:shop/babyInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/babyInfo", map);
    }

    /**
     * 获取商店种子信息
     * @param ctx 通道
     */
    public void seedInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<ShopSeedInfo> seedList = shopService.getSeedInfo(user);
                int level = buildService.getBuildLevel(user, 1);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("seedList", seedList);
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/seedInfo,msg:{}", e.getMessage());
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/seedInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取种子信息失败");
            e.printStackTrace();
            logger.error("method:shop/seedInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/seedInfo", map);
    }

    /**
     * 获取商店食物信息
     * @param ctx 通道
     */
    public void foodInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<ShopFoodInfo> foodList = shopService.getFoodInfo(user);
                int level = buildService.getBuildLevel(user, 1);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("foodList", foodList);
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/foodInfo,msg:{}", e.getMessage());
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/foodInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取食物信息失败");
            e.printStackTrace();
            logger.error("method:shop/foodInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/foodInfo", map);
    }

    /**
     * 获取自己货物信息
     * @param ctx
     */
    public void goodsInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                List<ShopGoodsInfo> goodsList = shopService.getUserGoodsInfo(user);
                int level = buildService.getBuildLevel(user, 1);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", level);
                map.put("goodsList", goodsList);
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/goodsInfo,msg:{}", e.getMessage());
        } catch (BuildException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/goodsInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取货物信息失败");
            e.printStackTrace();
            logger.error("method:shop/goodsInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/goodsInfo", map);
    }

    /**
     * 购买种子
     * @param ctx 通道
     * @param seedId 种子ID
     * @param count 数量
     */
    public void buySeed(ChannelHandlerContext ctx, Double seedId, Double count) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                shopService.buySeed(user, seedId.intValue(), count.intValue());
                map.put("success", true);
                map.put("message", "购买成功");
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/buySeed,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "购买种子失败");
            e.printStackTrace();
            logger.error("method:shop/buySeed,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/buySeed", map);
    }

    /**
     * 购买幼崽
     * @param ctx 通道
     * @param babyId 幼崽ID
     * @param count 数量
     */
    public void buyBaby(ChannelHandlerContext ctx, Double babyId, Double count) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                shopService.buyBaby(user, babyId.intValue(), count.intValue());
                map.put("success", true);
                map.put("message", "购买成功");
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/buyBaby,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "购买幼崽失败");
            e.printStackTrace();
            logger.error("method:shop/buyBaby,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/buyBaby", map);
    }

    /**
     * 购买食物
     * @param ctx 通道
     * @param foodId 食物ID
     * @param count 数量
     */
    public void buyFood(ChannelHandlerContext ctx, Double foodId, Double count) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                shopService.buyFood(user, foodId.intValue(), count.intValue());
                map.put("success", true);
                map.put("message", "购买成功");
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/buyFood,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "购买食物失败");
            e.printStackTrace();
            logger.error("method:shop/buyFood,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/buyFood", map);
    }

    /**
     * 售卖货物
     * @param ctx 通道
     * @param goodsId 货物ID
     * @param count 数量
     */
    public void sellGoods(ChannelHandlerContext ctx, Double goodsId, Double count) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                shopService.sellGoods(user, goodsId.intValue(), count.intValue());
                map.put("success", true);
                map.put("message", "购买成功");
            }
        } catch (ShopException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:shop/sellGoods,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "购买食物失败");
            e.printStackTrace();
            logger.error("method:shop/sellGoods,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "shop/sellGoods", map);
    }

}
