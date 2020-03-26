package org.milkteaboy.simplefarm.netty.entity;

import io.netty.channel.ChannelHandlerContext;

import java.io.*;

/**
 * Socket通信消息
 */
public class Message {

    // 消息类别，0为普通消息，99为心跳
    private Short type;
    // 内容
    private byte[] content;
    // 通道上下文
    private ChannelHandlerContext ctx;

    /**
     * @return 消息头长度
     */
    public static Integer getHeadLength() {
        return 4 + 2;
    }

    public Message() {

    }

    public Message(Short type, byte[] content, ChannelHandlerContext ctx) {
        super();
        this.type = type;
        this.content = content;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    /**
     * 获取消息长度
     * @return 消息长度
     */
    public int getMessageLength() {
        return getHeadLength() + getContent().length;
    }

}
