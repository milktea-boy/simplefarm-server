package org.milkteaboy.simplefarm.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.netty.constant.SocketErrorType;
import org.milkteaboy.simplefarm.netty.entity.Message;

/**
 * Socket错误消息事件处理
 */
public interface ISocketErrorEventHandler {

    public void handler(Message message, SocketErrorType errorType);

}
