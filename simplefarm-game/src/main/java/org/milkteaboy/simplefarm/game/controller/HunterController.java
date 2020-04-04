package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;

/**
 * hunter模块Controller
 */
@Controller("hunter")
public class HunterController {

    /**
     * 获取猎人小屋信息
     * @param ctx 通道
     */
    public void houseInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 放出猎人
     * @param ctx 通道
     */
    public void sendHunter(ChannelHandlerContext ctx) {

    }

    /**
     * 收获货物
     * @param ctx 通道
     */
    public void receiveGoods(ChannelHandlerContext ctx) {

    }

}
