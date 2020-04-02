package org.milkteaboy.simplefarm.game.config;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.config.SocketServerBean;
import org.milkteaboy.simplefarm.netty.constant.SocketActiveType;
import org.milkteaboy.simplefarm.netty.constant.SocketErrorType;
import org.milkteaboy.simplefarm.netty.entity.Message;
import org.milkteaboy.simplefarm.netty.handler.ISocketActiveHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketMessageEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfig {

    @Value("${socket.port}")
    private int port;

    private ISocketActiveHandler activeHandler = new ActiveHandler();
    private ISocketActiveHandler inactiveHandler = new ActiveHandler();
    private ISocketMessageEventHandler beforeDispatcherHandler = new BeforeDispatcherHandlerMessage();
    private ISocketErrorEventHandler errorHandler = new ErrorHandler();

    @Bean
    public SocketServerBean initSocketServer() {
        SocketServerBean socketServerBean = new SocketServerBean(port, activeHandler, inactiveHandler, beforeDispatcherHandler, errorHandler);
        return socketServerBean;
    }

    /**
     * 连接处理
     */
    class ActiveHandler implements ISocketActiveHandler {
        @Override
        public void handler(ChannelHandlerContext ctx, SocketActiveType activeType) {
            // 断线退出登录
            if (activeType == SocketActiveType.INACTIVE) {
                if (StaticData.userInfo.containsKey(ctx))
                    StaticData.userInfo.remove(ctx);
            }
        }
    }

    /**
     * 消息派发前处理
     */
    class BeforeDispatcherHandlerMessage implements ISocketMessageEventHandler {
        @Override
        public void handler(Message message) {

        }
    }

    /**
     * 错误处理
     */
    class ErrorHandler implements ISocketErrorEventHandler {
        @Override
        public void handler(Message message, SocketErrorType errorType) {

        }
    }

}
