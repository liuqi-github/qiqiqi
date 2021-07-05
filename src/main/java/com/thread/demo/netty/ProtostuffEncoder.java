package com.thread.demo.netty;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.thread.demo.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author LiuQi
 * @date 2021/1/20 17:00
 */
//用于将Person对象编码成字节数组  编码器 MessageToByteEncoder<NettyMessage>  序列化 对象转为数组数据
public class ProtostuffEncoder extends MessageToByteEncoder<NettyMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        Schema<NettyMessage> schema = RuntimeSchema.getSchema(NettyMessage.class);
        byte[] array = ProtobufIOUtil.toByteArray(msg, schema, buffer);
        out.writeBytes(array);
    }

}

