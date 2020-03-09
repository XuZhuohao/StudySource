package com.yui.study.netty.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 说明
 * 1.SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 的子类
 * 2.HttpObject 客户端和服务器端相互通讯的数据封装而成
 *
 * @author XuZhuohao
 * @date 2020-03-04 20:40
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取数据消息（这里我们可以读取客户端发送的消息）
     *
     * @param ctx 上下文对象，含有管道 pipeline ，通道 channel， 地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断 msg 是不是一个 HttpRequest 请求
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode" + ctx.pipeline().hashCode() +
                    " HttpServerHandler hash" + this.hashCode());
            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址=" + ctx.channel().remoteAddress());
            ///
//            System.out.println("内容：" + msg.toString());

            // 获取到HttpRequest
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            // 过滤 uri
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }

            // 回复信息给浏览器 [http 协议]
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器！", CharsetUtil.UTF_8);

            // 构造一个 http 的响应，即 HttpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构建好的 response 返回回去
            ctx.writeAndFlush(response);
        }
    }
}
