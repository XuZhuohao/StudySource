package com.yui.study.netty.demo2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author XuZhuohao
 * @date 2020-03-04 20:41
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器
        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();
        // 1.加入一个 netty 提供的 httpServerCodec => [coder - decoder]
        // HttpServerCodec 是 netty 提供的处理 http 的编-解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        // 2.加入自定义业务处理器
        pipeline.addLast("MyHttpServerHandler", new HttpServerHandler());
    }
}
