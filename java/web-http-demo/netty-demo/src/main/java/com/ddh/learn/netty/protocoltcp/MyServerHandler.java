package com.ddh.learn.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/16 16:01
 * @description:
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("服务端收到数据: " + new String(content, Charset.forName("UTF-8")) + " 当前个数为" + ++count);

        // 回送给客户端
        String message = UUID.randomUUID().toString();
        byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
        int length = bytes.length;

        MessageProtocol build = MessageProtocol.builder().len(length).content(bytes).build();
        ctx.writeAndFlush(build);
    }
}
