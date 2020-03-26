package org.milkteaboy.simplefarm.game.config;

import org.milkteaboy.simplefarm.netty.config.SocketServerBean;
import org.milkteaboy.simplefarm.netty.constant.SocketErrorType;
import org.milkteaboy.simplefarm.netty.entity.Message;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfig {

    @Value("${socket.port}")
    private int port;

    private ISocketEventHandler beforeDispatcherHandler = new BeforeDispatcherHandler();
    private ISocketErrorEventHandler errorHandler = new ErrorHandler();

    @Bean
    public SocketServerBean initSocketServer() {
        SocketServerBean socketServerBean = new SocketServerBean(port, beforeDispatcherHandler, errorHandler);
        return socketServerBean;
    }

    /**
     * 消息派发前处理
     */
    class BeforeDispatcherHandler implements ISocketEventHandler {
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
            System.out.println(errorType.getName());
        }
    }

}
