package com.yui.study.netty.demo1;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 1）实例要求：使用IDEA创建Netty项目
 * 2）Netty服务器在6668端口监听，客户端能发送消息给服务器"hello，服务器~”
 * 3）服务器可以回复消息给客户端"hello，客户端~”—
 * 4）目的：对Netty 线程模型有一个初步认识，便于理解Netty 模型理论
 * 5.1编写服务端
 * 5.2编写客户端
 * 5.3对netty程序进行分析，看看netty模型特点
 *
 * @author XuZhuohao
 * @date 2020-02-25 19:57
 */
public class SimpleServer {
    public static void main(String[] args) throws Exception {
        // 创建 BossGroup 和 WorkerGroup
        /*
        说明
        1.创建两个线程组 bossGroup 和 workerGroup
        2.bossGroup 值处理连接请求， 真正和客户端业务处理会交给 workerGroup
        3.这两个都是无限循环的
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 使用链式编程配置参数
            // 设置两个线程
            bootstrap.group(bossGroup, workerGroup)
                    // 使用 NioServerSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列得到连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 给我们的 workerGroup 的 EventLoop 对应的感到设置处理器
                    .childHandler(
                            // 匿名创建一个通道测试对象(实现 ChannelInitializer 接口)
                            new ChannelInitializer<SocketChannel>() {
                                // 给 pipeline 设置处理器
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline().addLast(new NettyServerHandler());
                                }
                            });
            System.out.println("....服务器 is ready");

            // 启动服务器并绑定一个端口，同步生成了一个 ChannelFuture 对象
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("绑定成功");
                } else {
                    System.err.println("绑定失败");
                }
            });
            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
