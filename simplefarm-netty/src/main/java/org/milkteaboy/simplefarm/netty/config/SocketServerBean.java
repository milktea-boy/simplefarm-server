package org.milkteaboy.simplefarm.netty.config;

import org.milkteaboy.simplefarm.netty.handler.ISocketActiveHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketMessageEventHandler;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Socket服务器bean
 */
public class SocketServerBean implements InitializingBean {

    // 端口号
    private int port = 8888;
    // 连接处理
    private ISocketActiveHandler activeHandler;
    // 关闭连接处理
    private ISocketActiveHandler inactiveHandler;
    // 消息派发前处理
    private ISocketMessageEventHandler beforeDispatcherHandler;
    // 错误处理
    private ISocketErrorEventHandler errorHandler;

    @Autowired
    private SocketServer socketServer;

    public SocketServerBean() {
    }

    public SocketServerBean(int port) {
        this.port = port;
    }

    public SocketServerBean(int port, ISocketActiveHandler activeHandler, ISocketActiveHandler inactiveHandler, ISocketMessageEventHandler beforeDispatcherHandler, ISocketErrorEventHandler errorHandler) {
        this.port = port;
        this.activeHandler = activeHandler;
        this.inactiveHandler = inactiveHandler;
        this.beforeDispatcherHandler = beforeDispatcherHandler;
        this.errorHandler = errorHandler;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ISocketActiveHandler getActiveHandler() {
        return activeHandler;
    }

    public void setActiveHandler(ISocketActiveHandler activeHandler) {
        this.activeHandler = activeHandler;
    }

    public ISocketActiveHandler getInactiveHandler() {
        return inactiveHandler;
    }

    public void setInactiveHandler(ISocketActiveHandler inactiveHandler) {
        this.inactiveHandler = inactiveHandler;
    }

    public ISocketMessageEventHandler getBeforeDispatcherHandler() {
        return beforeDispatcherHandler;
    }

    public void setBeforeDispatcherHandler(ISocketMessageEventHandler beforeDispatcherHandler) {
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
        socketServer.startServer(port, activeHandler, inactiveHandler, beforeDispatcherHandler, errorHandler);
    }

}
