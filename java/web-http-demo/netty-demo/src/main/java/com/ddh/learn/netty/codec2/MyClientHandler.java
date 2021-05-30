package com.ddh.learn.netty.codec2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/16 17:28
 * @description:
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当通道就绪时，可以发送数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 随机发送student对象或者worker对象
        int count = new Random().nextInt(2);
        System.out.println(count);
        MyDataInfo.MyMessage message = null;
        if (count == 0) {
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(count).setName("张三").build())
                    .build();
        } else {
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(count).setName("李四").build())
                    .build();
        }
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址: " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int ne = new Random().nextInt(3);
            System.out.println(ne);
        }

    }
}
