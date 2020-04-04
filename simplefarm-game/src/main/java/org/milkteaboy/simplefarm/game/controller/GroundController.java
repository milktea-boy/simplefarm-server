package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;

/**
 * ground模块Controller
 */
@Controller("ground")
public class GroundController {

    /**
     * 获取地块信息
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void groundInfo(ChannelHandlerContext ctx, Double groundId) {

    }

    /**
     * 播种
     * @param ctx 通道
     * @param groundId 地块ID
     * @param seedId 种子ID
     */
    public void sow(ChannelHandlerContext ctx, Double groundId, Double seedId) {

    }

    /**
     * 收获作物
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void reap(ChannelHandlerContext ctx, Double groundId) {

    }

    /**
     * 浇水
     * @param ctx 通道
     * @param groundId 地块ID
     */
    public void water(ChannelHandlerContext ctx, Double groundId) {

    }

}
