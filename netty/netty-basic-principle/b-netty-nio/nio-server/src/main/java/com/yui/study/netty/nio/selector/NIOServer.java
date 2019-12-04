package com.yui.study.netty.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author XuZhuohao
 * @date 2019-12-04 22:15
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        // 创建 ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 绑定端口 8081
        InetSocketAddress address = new InetSocketAddress(8081);
        serverChannel.socket().bind(address);
        // 获取一个 selector 对象
        Selector selector = Selector.open();
        // 设置为非阻塞
        serverChannel.configureBlocking(false);
        // 把 ServerSocketChannel 注册到 selector ，关心事件 OP_ACCEPT
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("begin again...");
            // 等待1秒钟。没有事件发生时放回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            // 事件发生,获取到相关的 SelectionKey 集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 通过 SelectionKey 反向获取通道
            // 遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取 SelectionKey
                SelectionKey selectionKey = keyIterator.next();
                System.out.println(String.format("isAcceptable;%s, isReadable:%s", selectionKey.isAcceptable(), selectionKey.isReadable()));
                // 根据 key 对应的通道发生的事件做对应的操作
                // 如果是 OP_ACCEPT，有新的客户端连接
                if (selectionKey.isAcceptable()) {
                    System.out.println("客户端连接成功");
                    // 给该客户端 生成一个 SocketChannel
                    SocketChannel socketChannel = serverChannel.accept();
                    // 只有设置为非阻塞才能向 selector 注册，否则 java.nio.channels.IllegalBlockingModeException
                    socketChannel.configureBlocking(false);
                    // 将 socketChannel 注册到 Selector,关注事件为 OP_READ，同时给 socketChannel 关联一个 buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    // 反向获取通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取到该 channel 关联的 buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("form 客户端 " + new String(buffer.array()));
                }
                // 手动从集合中移出当前的 selectionKey 防止重复操作
                keyIterator.remove();
            }

        }

    }

}
