package org.milkteaboy.simplefarm.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.milkteaboy.simplefarm.netty.entity.Message;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(message.getMessageLength());
        byteBuf.writeShort(message.getType());
        byteBuf.writeBytes(message.getContent());
        ctx.flush();
    }

}
