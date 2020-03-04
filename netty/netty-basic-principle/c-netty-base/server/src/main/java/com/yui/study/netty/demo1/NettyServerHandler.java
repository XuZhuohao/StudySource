package com.yui.study.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 1.我们自定义一个 Handler 需要继承 netty 规定好的某个 HandlerAdapter
 * 2.这时我们自定义一个 Handler，才能称为一个 handler
 *
 * @author XuZhuohao
 * @date 2020-02-25 19:19
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据消息（这里我们可以读取客户端发送的消息）
     *
     * @param ctx 上下文对象，含有管道 pipeline ，通道 channel， 地址
     * @param msg 客户端发送的数据，默认是 Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        // 将 msg 转成一个 ByteBuf
        // ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());


        /// 这里有一个非常耗费时间的业务 -> 异步执行 -> 提交到该 channel 对应的 NioEventLoop 的taskQueue
//        Thread.sleep(10 * 1000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("finish, 客户端~", CharsetUtil.UTF_8));
        // 解决方案1 用户程序自定义普通任务
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("finish, 客户端~", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 解决方案2 用户自定义定时任务 - scheduleTaskQueue
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("finish2, 客户端~", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS );
    }

    /**
     * 数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // writeAndFlush 是 write + flush
        // 将数据写入到缓存， 并刷新
        // 一般讲，我们需要对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
