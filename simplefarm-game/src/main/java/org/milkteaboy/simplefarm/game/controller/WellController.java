package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.UserService;
import org.milkteaboy.simplefarm.service.WellService;
import org.milkteaboy.simplefarm.service.constant.Constant;
import org.milkteaboy.simplefarm.service.exception.WellException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * well模块Controller
 */
@Controller("well")
public class WellController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private WellService wellService;

    private static Logger logger = LoggerFactory.getLogger(WellController.class);

    /**
     * 收获水
     * @param ctx 通道
     */
    public void reap(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = StaticData.userInfo.get(ctx.channel());
            if (user == null) {
                map.put("success", false);
                map.put("message", "用户未登录");
            } else {
                int count = wellService.reap(user);
                map.put("success", true);
                map.put("message", "收获成功");
                map.put("count", count);
            }
        } catch (WellException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
            logger.error("method:well/reap,msg:{}", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "收获失败");
            e.printStackTrace();
            logger.error("method:well/reap,msg:{}", e.getMessage());
        }
        socketServer.sendMessage(ctx, "well/reap", map);
    }

}
