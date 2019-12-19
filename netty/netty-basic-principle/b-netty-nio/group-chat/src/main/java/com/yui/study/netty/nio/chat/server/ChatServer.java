package com.yui.study.netty.nio.chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author XuZhuohao
 * @date 2019-12-19 21:39
 */
public class ChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int port = 6667;

    public ChatServer() {
        try {
            // 1.获取一个 channel 用于处理接受连接
            this.listenChannel = ServerSocketChannel.open();
            // 1.1 绑定到对应端口
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            this.listenChannel.socket().bind(inetSocketAddress);
            // 2.声明一个 selector 用于接受连接事件
            this.selector = Selector.open();
            // 3.设置为非阻塞 io
            this.listenChannel.configureBlocking(false);
            // 为 selector 注册事件
            this.listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                if (selector.select() == 0) {
                    System.out.println("等待连接中...");
                    continue;
                }
                // 获取事件key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 循环处理所有事件
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 如果为连接事件
                    if (key.isAcceptable()) {
                        // 给该客户端 生成一个 SocketChannel
                        SocketChannel sc = listenChannel.accept();
                        // 只有设置为非阻塞才能向 selector 注册，否则 java.nio.channels.IllegalBlockingModeException
                        sc.configureBlocking(false);
                        // 将 socketChannel 注册到 Selector,关注事件为 OP_READ，同时给 socketChannel 关联一个 buffer
                        sc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        // 提升
                        System.out.println(sc.getRemoteAddress() + " 上线了");
                    }
                    // 可读
                    if (key.isReadable()) {
                        // 处理读
                        readData(key);
                    }

                    iterator.remove();
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 读取消息
     * @param key
     */
    private void readData(SelectionKey key){
        // 定义一个 sockeChannel
        SocketChannel channel = null;
        try {
           channel = (SocketChannel) key.channel();
           // 创建 buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                /// 把缓存区的数据转成字符串
//                buffer.flip();
                String msg = new String(buffer.array());
                System.out.println("from 客户端：" + msg);
                // 向其他客户端转发消息
                sendInfoToOhterClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                // 取消注册
                key.channel();
                // 关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 发送消息给其他客户端
     * @param msg
     * @param self
     */
    public void sendInfoToOhterClients(String msg, SocketChannel self){
        // 服务器转发消息
        System.out.println("服务器转发消息..");
        // 遍历所有 key
       this.selector.keys().forEach(key->{
           Channel targetChannel = key.channel();
           //排除自己
           if (targetChannel instanceof SocketChannel && !targetChannel.equals(self)){
               SocketChannel dest = (SocketChannel) targetChannel;
               // 将 msg 存到 buffer
               ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
//               buffer.flip();
               try {
                   dest.write(buffer);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });

    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }
}
