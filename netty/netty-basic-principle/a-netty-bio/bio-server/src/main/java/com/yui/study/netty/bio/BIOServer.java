package com.yui.study.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author XuZhuohao
 * @date 2019-11-19 22:04
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        //
        ExecutorService executor = Executors.newCachedThreadPool();
        // 创建 ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while(true){
            // 监听，等待客户端连接
            final Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端了。");
            // 创建一个线程池与之通信
            executor.execute(new Runnable() {
                public void run() {
                    // 可以和客户端通讯
                    handler(accept);
                }
            });
        }
    }
    /**
     * handler 方法，可以和客户端通讯
     * @param socket 连接
     */
    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        // 通过 socket 获取输入流
        try {
            System.out.println(String.format("线程信息 id = %d, 名字=%s", Thread.currentThread().getId(), Thread.currentThread().getName()));
            InputStream inputStream = socket.getInputStream();
            while (true){
                System.out.println("阻塞，等待数据输入 read...");
                // read 如果没有数据会阻塞在这里
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和客户端的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
