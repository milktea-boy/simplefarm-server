package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.HunterService;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.dto.HunterInfo;
import org.milkteaboy.simplefarm.service.dto.HunterReapInfo;
import org.milkteaboy.simplefarm.service.exception.HunterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * hunter模块Controller
 */
@Controller("hunter")
public class HunterController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private HunterService hunterService;
    @Autowired
    private UserService userService;

    /**
     * 获取猎人小屋信息
     * @param ctx 通道
     */
    public void houseInfo(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                HunterInfo hunterInfo = hunterService.getHunterInfo(user);
                map.put("success", true);
                map.put("message", "获取成功");
                map.put("buildLevel", hunterInfo.getBuildLevel());
                int state = hunterInfo.getState();
                map.put("state", state);
                if (state == 1) {
                    map.put("startDateTime", hunterInfo.getStartDateTime());
                    map.put("finishDateTime", hunterInfo.getFinishDateTime());
                } else if (state == 2) {
                    map.put("goodsId", hunterInfo.getGoodsId());
                    map.put("count", hunterInfo.getCount());
                }
            }
        } catch (HunterException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "获取猎人小屋信息失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "hunter/houseInfo", map);
    }

    /**
     * 放出猎人
     * @param ctx 通道
     */
    public void sendHunter(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                hunterService.sendHunter(user);
                map.put("success", true);
                map.put("message", "放出成功");
            }
        } catch (HunterException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "放出猎人失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "hunter/sendHunter", map);
    }

    /**
     * 收获货物
     * @param ctx 通道
     */
    public void receiveGoods(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = StaticData.userInfo.get(ctx);
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                HunterReapInfo hunterReapInfo = hunterService.receiveHunter(user);

                // 增加经验和升级推送
                boolean isLevelup = userService.addUserExp(user, Constant.HUNTER_RECEIVE_EXP);
                if (isLevelup)
                    socketServer.sendMessage(ctx, "<levelup>", new HashMap<>());

                map.put("success", true);
                map.put("message", "收获成功");
                map.put("goodsId", hunterReapInfo.getGoodsId());
                map.put("count", hunterReapInfo.getCount());
            }
        } catch (HunterException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "收回猎人失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "hunter/receiveGoods", map);
    }

}
