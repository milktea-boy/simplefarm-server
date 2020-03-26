package org.milkteaboy.simplefarm.netty.config;

import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketEventHandler;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Socket服务器bean
 */
public class SocketServerBean implements InitializingBean {

    // 端口号
    private int port = 8888;
    // 消息派发前处理
    private ISocketEventHandler beforeDispatcherHandler;
    // 错误处理
    private ISocketErrorEventHandler errorHandler;

    @Autowired
    private SocketServer socketServer;

    public SocketServerBean() {
    }

    public SocketServerBean(int port) {
        this.port = port;
    }

    public SocketServerBean(int port, ISocketEventHandler beforeDispatcherHandler, ISocketErrorEventHandler errorHandler) {
        this.port = port;
        this.beforeDispatcherHandler = beforeDispatcherHandler;
        this.errorHandler = errorHandler;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ISocketEventHandler getBeforeDispatcherHandler() {
        return beforeDispatcherHandler;
    }

    public void setBeforeDispatcherHandler(ISocketEventHandler beforeDispatcherHandler) {
        this.beforeDispatcherHandler = beforeDispatcherHandler;
    }

    public ISocketErrorEventHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ISocketErrorEventHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        socketServer.startServer(port, beforeDispatcherHandler, errorHandler);
    }

}
