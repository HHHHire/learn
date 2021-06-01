package com.ddh.learn.netty.handler;

import com.ddh.learn.netty.codec2.MyDataInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.TypeParameterMatcher;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 1:58
 * @description:
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * 解码
     * decode 会根据接收到的数据，别调用多次，直到bytebuf没有更多的可读字节为止，
     * 如果out不为空，会将其内容传递给下一个handler，当然也会被调用多次
     *
     * @param ctx
     * @param in 入站数据
     * @param out 解码后的数据，会传递给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }

    public static void main(String[] args) {
        MyDataInfo.MyMessage msg = MyDataInfo.MyMessage.newBuilder()
                .setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                .setStudent(MyDataInfo.Student.newBuilder().setId(2).setName("阿迪斯").build())
                .build();
        TypeParameterMatcher matcher = TypeParameterMatcher.get(MyDataInfo.MyMessage.class);
        System.out.println(matcher.match(msg));

        TypeParameterMatcher matcher1 = TypeParameterMatcher.get(Integer.class);
        System.out.println(matcher1.match(12L));

    }
}
