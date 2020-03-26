package org.milkteaboy.simplefarm.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.milkteaboy.simplefarm.netty.entity.Message;

import java.util.List;

/**
 * 消息解码器
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 如果接收到的数据长度小于数据包头长度，暂不处理
        if (in.readableBytes() < Message.getHeadLength()) {
            return;
        }

        in.markReaderIndex();
        // 获取数据包长度
        int length = in.readInt();
        // 如果接收到的数据未到数据包长度，则暂不处理
        if (in.readableBytes() < length - 4) {
            in.resetReaderIndex();
            return;
        }

        // 解析消息
        Message message = new Message();
        message.setType(in.readShort());
        // 获取数据
        byte[] data = new byte[length - Message.getHeadLength()];
        in.readBytes(data, 0, data.length);
        message.setContent(data);
        message.setCtx(ctx);

        out.add(message);
    }

}
