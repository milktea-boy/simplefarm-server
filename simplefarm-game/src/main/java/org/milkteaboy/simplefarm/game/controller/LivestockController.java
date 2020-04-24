package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.LivestockService;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.LivestockInfo;
import org.milkteaboy.simplefarm.service.dto.LivestockReapInfo;
import org.milkteaboy.simplefarm.service.exception.LivestockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * livestock模块Controller
 */
@Controller("livestock")
public class LivestockController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private LivestockService livestockService;

    private static Logger logger = LoggerFactory.getLogger(LivestockController.class);

    /**
     * 获取畜舍信息
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void livestockInfo(ChannelHandlerContext ctx, Double livestockId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                LivestockInfo livestockInfo = livestockService.getLivestockInfo(user, livestockId.intValue());
                map.put("success", true);
                map.put("message", "获取畜舍信息成功");
                map.put("buildLevel", livestockInfo.getBuildLevel());
                int state = livestockInfo.getState();
                map.put("state", state);
                if (state == 0) {
                    map.put("babyList", livestockInfo.getBabyList());
                    map.put("maxPopulation", livestockInfo.getMaxPopuplation());
                } else if (state == 1) {
                    map.put("startDateTime", livestockInfo.getStartDateTime());
                    map.put("finishDateTime", livestockInfo.getFinishDateTime());
                    map.put("feedCount", livestockInfo.getFeedCount());
                    map.put("feedMaxCount", livestockInfo.getFeedMaxCount());
                } else if (state == 2) {
                    map.put("goodsId", livestockInfo.getGoodsId());
                    map.put("count", livestockInfo.getGoodsId());
                }
            }
        } catch (LivestockException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:livestock/livestockInfo,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取畜舍信息失败");
            e.printStackTrace();
            logger.error("method:livestock/livestockInfo,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "livestock/livestockInfo", map);
    }

    /**
     * 养殖动物
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     * @param babyId 幼崽ID
     * @param count 幼崽数量
     */
    public void breed(ChannelHandlerContext ctx, Double livestockId, Double babyId, Double count) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                livestockService.breed(user, livestockId.intValue(), babyId.intValue(), count.intValue());
                map.put("success", true);
                map.put("message", "喂养成功");
            }
        } catch (LivestockException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:livestock/breed,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "养殖失败");
            e.printStackTrace();
            logger.error("method:livestock/breed,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "livestock/breed", map);
    }

    /**
     * 收获动物
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void reap(ChannelHandlerContext ctx, Double livestockId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                LivestockReapInfo livestockReapInfo = livestockService.reap(user, livestockId.intValue());
                map.put("success", true);
                map.put("message", "收获成功");
                map.put("id", livestockReapInfo.getId());
                map.put("count", livestockReapInfo.getCount());
            }
        } catch (LivestockException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:livestock/reap,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "收获失败");
            e.printStackTrace();
            logger.error("method:livestock/reap,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "livestock/reap", map);
    }

    /**
     * 喂养
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void feed(ChannelHandlerContext ctx, Double livestockId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                livestockService.feed(user, livestockId.intValue());
                map.put("success", true);
                map.put("message", "喂养成功");
            }
        } catch (LivestockException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:livestock/feed,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "喂养失败");
            e.printStackTrace();
            logger.error("method:livestock/feed,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "livestock/feed", map);
    }

}
