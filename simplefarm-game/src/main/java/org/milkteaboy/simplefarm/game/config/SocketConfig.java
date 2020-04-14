package org.milkteaboy.simplefarm.game.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.config.SocketServerBean;
import org.milkteaboy.simplefarm.netty.constant.Constant;
import org.milkteaboy.simplefarm.netty.constant.SocketActiveType;
import org.milkteaboy.simplefarm.netty.constant.SocketErrorType;
import org.milkteaboy.simplefarm.netty.entity.Message;
import org.milkteaboy.simplefarm.netty.handler.ISocketActiveHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketMessageEventHandler;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SocketConfig {

    @Value("${socket.port}")
    private int port;

    private ISocketActiveHandler activeHandler = new ActiveHandler();
    private ISocketActiveHandler inactiveHandler = new ActiveHandler();
    private ISocketMessageEventHandler beforeDispatcherHandler = new BeforeDispatcherHandlerMessage();
    private ISocketErrorEventHandler errorHandler = new ErrorHandler();

    @Resource
    private SocketServer socketServer;

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
                if (StaticData.userInfo.containsKey(ctx)) {
                    User user = StaticData.userInfo.get(ctx);
                    if (StaticData.userTempInfo.containsKey(user))
                        StaticData.userTempInfo.remove(user);

                    StaticData.userInfo.remove(ctx);
                }
            }
        }
    }

    /**
     * 消息派发前处理
     */
    class BeforeDispatcherHandlerMessage implements ISocketMessageEventHandler {
        @Override
        public void handler(Message message) {
            if (message.getType() == 99)
                return;

            JSONObject jo = null;
            try {
                jo = JSON.parseObject(new String(message.getContent(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String methodName = jo.getString("method");
            if (methodName != null) {
                if (methodName.indexOf("login") != -1)
                    return;
                if (StaticData.userInfo.containsKey(message.getCtx()))
                    return;

                Map<String, Object> map = new HashMap();
                map.put("success", false);
                map.put("message", "未登录");
                socketServer.sendMessage(message.getCtx(), methodName, map);
            }
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
