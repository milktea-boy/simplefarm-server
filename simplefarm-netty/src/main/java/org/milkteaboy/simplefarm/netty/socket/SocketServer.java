package org.milkteaboy.simplefarm.netty.socket;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.milkteaboy.simplefarm.netty.entity.Message;
import org.milkteaboy.simplefarm.netty.handler.ISocketActiveHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketMessageEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Socket服务器
 */
@Component("socketServer")
@Scope("singleton")
public class SocketServer {

    // 通道组
    public static ChannelGroup channelGroup;
    // 连接处理
    public static ISocketActiveHandler activeHandler;
    // 关闭连接处理
    public static ISocketActiveHandler inactiveHandler;
    // 消息派发前事件
    public static ISocketMessageEventHandler beforeDispatcherHandler;
    // 消息处理错误事件
    public static ISocketErrorEventHandler messageErrorHandler;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ChannelFuture channelFuture;

    private static Logger logger = LoggerFactory.getLogger(SocketServer.class);

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /**
     * 启动服务器
     * @param port 端口
     * @param beforeDispatcherHandler 消息派发前事件
     * @param errorEventHandler 消息处理错误事件
     */
    public void startServer(int port, ISocketActiveHandler activeHandler, ISocketActiveHandler inactiveHandler, ISocketMessageEventHandler beforeDispatcherHandler, ISocketErrorEventHandler errorEventHandler) {
        this.activeHandler = activeHandler;
        this.inactiveHandler = inactiveHandler;
        this.beforeDispatcherHandler = beforeDispatcherHandler;
        this.messageErrorHandler = errorEventHandler;

        try {
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChildChannelHandler())
                    .option(ChannelOption.SO_BACKLOG, 2048)
                    .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            channelFuture = b.bind(port).sync();
        } catch (Exception e) {
            try {
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 停止服务器
     */
    public void stopServer() {
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    /**
     * 给单个用户发送消息
     * @param ctx 通道
     * @param method 接口名
     * @param data 数据
     */
    public void sendMessage(ChannelHandlerContext ctx, String method, Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>();
        map.put("method", method);
        map.put("data", data);

        Message message = new Message();
        message.setCtx(ctx);
        message.setType(Short.valueOf("0"));
        String json = JSON.toJSONString(map);
        message.setContent(json.getBytes());

        ctx.channel().writeAndFlush(message);
        logger.debug("发送消息：{}", json);
    }

    /**
     * 给所有用户群发消息
     * @param method 接口名
     * @param data 数据
     */
    public void sendMessageToAllUser(String method, Map<String, Object> data) {
        Map<String, Object> map = new HashMap<>();
        map.put("method", method);
        map.put("data", data);

        Message message = new Message();
        message.setType(Short.valueOf("0"));
        String json = JSON.toJSONString(map);
        message.setContent(json.getBytes());

        channelGroup.writeAndFlush(message);
        logger.debug("发送群发消息：{}", json);
    }

}
