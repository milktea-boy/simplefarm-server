package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;

/**
 * shop模块Controller
 */
@Controller("shop")
public class ShopController {

    /**
     * 获取商店幼崽信息
     * @param ctx 通道
     */
    public void babyInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 获取商店种子信息
     * @param ctx 通道
     */
    public void seedInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 获取商店食物信息
     * @param ctx 通道
     */
    public void foodInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 获取自己货物信息
     * @param ctx
     */
    public void goodsInfo(ChannelHandlerContext ctx) {

    }

    /**
     * 购买种子
     * @param ctx 通道
     * @param seedId 种子ID
     * @param count 数量
     */
    public void buySeed(ChannelHandlerContext ctx, Double seedId, Double count) {

    }

    /**
     * 购买幼崽
     * @param ctx 通道
     * @param babyId 幼崽ID
     * @param count 数量
     */
    public void buyBaby(ChannelHandlerContext ctx, Double babyId, Double count) {

    }

    /**
     * 售卖货物
     * @param ctx 通道
     * @param goodsId 货物ID
     * @param count 数量
     */
    public void sellGoods(ChannelHandlerContext ctx, Double goodsId, Double count) {

    }

}
