package com.ddh.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 1:18
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String msg;
            switch (event.state()) {
                case READER_IDLE:
                    msg = "读空闲";
                    break;
                case WRITER_IDLE:
                    msg = "写空闲";
                    break;
                case ALL_IDLE:
                    msg = "读写空闲";
                    break;
                default:
                    msg = "null";
            }
            System.out.println(ctx.channel().remoteAddress() + msg);
            // 可以关闭通道
//            ctx.channel().close();
        }
    }
}
