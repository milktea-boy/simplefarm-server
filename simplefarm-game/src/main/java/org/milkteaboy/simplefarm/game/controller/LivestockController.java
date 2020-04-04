package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;

/**
 * livestock模块Controller
 */
@Controller("livestock")
public class LivestockController {

    /**
     * 获取畜舍信息
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void livestockInfo(ChannelHandlerContext ctx, Double livestockId) {

    }

    /**
     * 养殖动物
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     * @param babyId 幼崽ID
     * @param count 幼崽数量
     */
    public void breed(ChannelHandlerContext ctx, Double livestockId, Double babyId, Double count) {

    }

    /**
     * 收获动物
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void reap(ChannelHandlerContext ctx, Double livestockId) {

    }

    /**
     * 喂养
     * @param ctx 通道
     * @param livestockId 畜舍ID，畜舍1为0，畜舍2为1
     */
    public void feed(ChannelHandlerContext ctx, Double livestockId) {

    }

}
