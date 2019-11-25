package com.yui.study.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author XuZhuohao
 * @date 2019-11-25 21:56
 */
public class NioFileChannel {
    public static void main(String[] args) {
        case1();
        case2();
        case3();

    }

    public static void case1() {
        String str = "hello world";
        // 创建一个输出流 -> channel
        try (FileOutputStream os = new FileOutputStream("G:\\Cache\\code\\file01.txt")) {
            // 获取 FileChannel
            FileChannel channel = os.getChannel();
            // 创建一个缓冲
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 将 str 放到 byteBuffer 中
            byteBuffer.put(str.getBytes());
            // 翻转
            byteBuffer.flip();
            // 从缓冲区写到通道中
            channel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void case2() {
        try (FileInputStream fis = new FileInputStream("G:\\Cache\\code\\file01.txt")) {
            FileChannel channel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array()));
                /// byteBuffer.array() 不会影响 Buffer 的属性
//                byteBuffer.flip();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void case3() {
        try (FileOutputStream os = new FileOutputStream("G:\\Cache\\code\\file01Copy.txt");
             FileInputStream fis = new FileInputStream("G:\\Cache\\code\\file01.txt")) {
            FileChannel osChannel = os.getChannel();
            FileChannel fisChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            while (fisChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                osChannel.write(byteBuffer);
                byteBuffer.flip();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
