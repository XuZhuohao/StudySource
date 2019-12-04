package com.yui.study.netty.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author XuZhuohao
 * @date 2019-12-04 22:43
 */
public class NIOClinet {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8081);
        if (!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作。。。");
            }
        }
        // 如果连接成功，就发送数据
        System.out.println("link ");
        String str = "hello world~";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据，把 buffer 写入 channel
        socketChannel.write(byteBuffer);
        System.in.read();
        str += " again";
        byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        System.in.read();

    }
}
