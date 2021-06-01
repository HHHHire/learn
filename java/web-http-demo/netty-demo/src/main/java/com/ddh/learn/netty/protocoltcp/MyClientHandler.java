package com.ddh.learn.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/16 17:28
 * @description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    /**
     * 当通道就绪时，可以发送数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送消息");
        for (int i = 0; i < 5; i++) {
            String message = "啊手动阀手动阀";
            byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
            MessageProtocol msg = MessageProtocol.builder().len(bytes.length).content(bytes).build();
            ctx.writeAndFlush(msg);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        System.out.println("客户端接收到数据: " + new String(msg.getContent(), CharsetUtil.UTF_8) + " 当前包数为: " + ++count);

    }
}
