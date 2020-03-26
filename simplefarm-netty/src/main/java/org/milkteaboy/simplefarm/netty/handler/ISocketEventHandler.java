package org.milkteaboy.simplefarm.netty.handler;

import org.milkteaboy.simplefarm.netty.entity.Message;

/**
 * Socket消息事件处理
 */
public interface ISocketEventHandler {

    public void handler(Message message);

}
