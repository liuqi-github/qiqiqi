package com.thread.demo.netty;

import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.thread.demo.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.*;

/**
 * @author LiuQi
 * @date 2021/1/20 17:00
 */
//用将ByteBuf中的数组数据转换成自定义对象  解码器 ByteToMessageDecoder  数组数据进行反序列化成对象
public class ProtostuffDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Schema<NettyMessage> schema = RuntimeSchema.getSchema(NettyMessage.class);
        NettyMessage nettyMessage = schema.newMessage();
        byte[] array = new byte[in.readableBytes()];
        in.readBytes(array);
        ProtobufIOUtil.mergeFrom(array, nettyMessage, schema);
        out.add(nettyMessage);
    }

}
