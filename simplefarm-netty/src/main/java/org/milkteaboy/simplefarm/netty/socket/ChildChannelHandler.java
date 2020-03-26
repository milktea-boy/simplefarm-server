package org.milkteaboy.simplefarm.netty.socket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new MessageDecoder());
        socketChannel.pipeline().addLast(new MessageEncoder());
        socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0));
        socketChannel.pipeline().addLast(new SocketChannelHandler());
    }

}
