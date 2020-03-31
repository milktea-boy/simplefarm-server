package org.milkteaboy.simplefarm.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.netty.constant.SocketActiveType;

/**
 * Socket激活处理
 */
public interface ISocketActiveHandler {

    void handler(ChannelHandlerContext ctx, SocketActiveType activeType);

}
