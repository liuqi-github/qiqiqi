package com.thread.demo.netty;

import com.thread.demo.entity.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiuQi
 * @date 2021/1/20 14:51
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private int counter = 0;

    /**
     * 客户端数据到来时触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/*        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client request: " + buf.toString(CharsetUtil.UTF_8));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String callback = sf.format(new Date());
        String callback = sf.format(new Date()) + "\n";
        ctx.write(Unpooled.copiedBuffer(callback.getBytes()));*/

        NettyMessage person = (NettyMessage) msg;
        log.info("当前是第[{}]次获取到客户端发送过来的person对象[{}].", ++counter, person.toString());
        Thread.sleep(1000);
        ctx.write(person);
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将发送缓冲区的消息全部写到SocketChannel中
        ctx.flush();
    }

    /**
     * 发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 释放与ChannelHandlerContext相关联的资源
        ctx.close();
    }


}
