package com.yui.study.netty.basic.client.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author XuZhuohao
 * @date 2019/11/15
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * ChannelHandlerContext 对象提供了许多操作，使你能够触发各种各样的 I/O 事件和操作。
         * 这里我们调用了 write(Object) 方法来逐字地把接受到的消息写入。
         * 请注意不同于 DISCARD 的例子我们并没有释放接受到的消息，这是因为当写入的时候 Netty 已经帮我们释放了
         */
        ByteBuf in = (ByteBuf) msg;
        System.out.println("echo:" + in.toString(io.netty.util.CharsetUtil.US_ASCII));
        /**
         * ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，
         * 你需要调用 ctx.flush() 方法来把缓冲区中数据强行输出。
         * 或者你可以用更简洁的 cxt.writeAndFlush(msg) 以达到同样的目的。
         */
        ctx.write(msg);
        ctx.flush();
    }

}
