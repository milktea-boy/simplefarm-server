package org.milkteaboy.simplefarm.netty.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.milkteaboy.simplefarm.netty.constant.Constant;
import org.milkteaboy.simplefarm.netty.constant.SocketActiveType;
import org.milkteaboy.simplefarm.netty.constant.SocketErrorType;
import org.milkteaboy.simplefarm.netty.entity.Message;
import org.milkteaboy.simplefarm.netty.exception.ArgsParseException;
import org.milkteaboy.simplefarm.netty.handler.ISocketErrorEventHandler;
import org.milkteaboy.simplefarm.netty.handler.ISocketMessageEventHandler;
import org.milkteaboy.simplefarm.netty.util.SpringUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Socket通道事件处理
 */
public class SocketChannelHandler extends ChannelInboundHandlerAdapter {

    // 未收到心跳次数
    private int unRecPingTimes = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketServer.channelGroup.add(ctx.channel());
        if (SocketServer.activeHandler != null)
            SocketServer.activeHandler.handler(ctx, SocketActiveType.ACTIVE);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SocketServer.channelGroup.remove(ctx.channel());
        if (SocketServer.activeHandler != null)
            SocketServer.activeHandler.handler(ctx, SocketActiveType.INACTIVE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        unRecPingTimes = 0;
        ISocketMessageEventHandler eventHandler = SocketServer.beforeDispatcherHandler;

        Message message = (Message) msg;

        if (eventHandler != null)
            eventHandler.handler(message);

        if (message.getType() == 0)
            dispatcherMessage(message);
        else if (message.getType() == 99)
            responseHeart(message);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 超过3次未接收到心跳，则关闭连接
        if (evt instanceof IdleStateEvent) {
            if (((IdleStateEvent) evt).state() == IdleState.READER_IDLE) {
                unRecPingTimes++;
                if (unRecPingTimes > 3) {
                    ctx.channel().close();
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            ctx.close();
        }
    }

    /**
     * 分发消息
     * 自动调用对应方法，映射关系：method名称为model/interfacename对应beanId/method
     * @param message 消息
     */
    public void dispatcherMessage(Message message) {
        ISocketErrorEventHandler errorEventHandler = SocketServer.messageErrorHandler;

        JSONObject jo = null;
        try {
            jo = JSON.parseObject(new String(message.getContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String methodName = jo.getString("method");
        Object[] datas = jo.getJSONArray("data").toArray();
        String[] urls = methodName.split("/");
        String modelName = urls[0];
        String interfaceName = urls[1];
        Object[] args = getArgs(datas, message.getCtx());
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        try {
            Object bean = applicationContext.getBean(modelName);
            Method method = null;
            method = bean.getClass().getMethod(interfaceName, getClasses(args));
            method.invoke(bean, args);
        } catch (NoSuchBeanDefinitionException e) {
            if (errorEventHandler != null)
                errorEventHandler.handler(message, SocketErrorType.METHOD_ERROR);
        } catch (ArgsParseException e) {
            if (errorEventHandler != null)
                errorEventHandler.handler(message, SocketErrorType.ARGS_ERROR);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (errorEventHandler != null)
                errorEventHandler.handler(message, SocketErrorType.ARGS_ERROR);
        } catch (NoSuchMethodException e) {
            if (errorEventHandler != null)
                errorEventHandler.handler(message, SocketErrorType.METHODORARGS_ERROR);
        }
    }

    /**
     * 获取参数列表
     * @param jsonData
     * @param ctx
     * @return
     */
    private Object[] getArgs(Object[] jsonData, ChannelHandlerContext ctx) {
        if (jsonData == null)
            return null;

        Object[] args = new Object[jsonData.length + 1];
        for (int i = 0; i < args.length; i++) {
            if (i == 0)
                args[i] = ctx;
            else
                args[i] = jsonData[i - 1];
        }

        return args;
    }

    /**
     * 获取参数列表类型
     * @param args
     * @return
     */
    private Class<?>[] getClasses(final Object[] args) throws ArgsParseException {
        try {
            Class<?>[] classArray = new Class<?>[args.length];

            for (int i = 0; i < args.length; i++) {
                classArray[i] = args[i].getClass();

                if (args[i] instanceof Integer) {
                    classArray[i] = Double.class;
                    args[i] = ((Integer) args[i]).doubleValue();
                } else if (args[i] instanceof Long) {
                    classArray[i] = Double.class;
                    args[i] = ((Long) args[i]).doubleValue();
                } else if (args[i] instanceof Float) {
                    classArray[i] = Double.class;
                    args[i] = ((Float) args[i]).doubleValue();
                } else if (args[i] instanceof Double) {
                    classArray[i] = Double.class;
                } else if (args[i] instanceof BigDecimal) {
                    classArray[i] = Double.class;
                    args[i] = ((BigDecimal) args[i]).doubleValue();
                } else if (args[i] instanceof Boolean) {
                    classArray[i] = Boolean.class;
                } else if (args[i] instanceof Map) {
                    classArray[i] = Map.class;
                } else if (args[i] instanceof ChannelHandlerContext) {
                    classArray[i] = ChannelHandlerContext.class;
                }
            }
            return classArray;
        } catch (Exception e) {
            throw new ArgsParseException();
        }
    }

    /**
     * 回复心跳
     * @param message 消息
     */
    private void responseHeart(Message message) {
        Message responseMessage = new Message();
        responseMessage.setType(Short.valueOf("99"));
        responseMessage.setContent(Constant.socketHeartResponse.getBytes());
        responseMessage.setCtx(message.getCtx());

        message.getCtx().channel().writeAndFlush(responseMessage);
    }

}
