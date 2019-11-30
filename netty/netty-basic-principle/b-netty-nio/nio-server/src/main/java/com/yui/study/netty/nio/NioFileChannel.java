package com.yui.study.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author XuZhuohao
 * @date 2019-11-25 21:56
 */
public class NioFileChannel {
    public static void main(String[] args) throws Exception {
//        case1();
//        case2();
//        case3();
//        case4();
//        readOnly();
//        mappedByteBuffer();
        scatteringAndGathering();
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
                byteBuffer.clear();
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
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void case4() {
        try (FileOutputStream os = new FileOutputStream("G:\\Cache\\code\\picCopy.jpg");
             FileInputStream fis = new FileInputStream("G:\\Cache\\code\\pic.jpg")) {
            FileChannel osChannel = os.getChannel();
            FileChannel fisChannel = fis.getChannel();
            osChannel.transferFrom(fisChannel, 0, fisChannel.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readOnly() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.get());
        // .ReadOnlyBufferException
        readOnlyBuffer.putInt(1);
    }

    public static void mappedByteBuffer() {
        try {
            // 可以让文件直接在内存(堆外内存)修改，操作系统不需要拷贝一次
//        MappedByteBuffer.allocate()
            RandomAccessFile randomAccessFile = new RandomAccessFile("G:\\Cache\\code\\map.txt", "rw");
            FileChannel channel = randomAccessFile.getChannel();
            /**
             * FileChannel.MapMode.READ_WRITE： 使用读写模式
             * 0 ：可以直接修改的起始位置
             * 5 ： 是映射到内存的大小，即将1.txt的多少个字节映射到内存
             * 数据类型： java.nio.DirectByteBuffer
             */
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            map.put(0, (byte) 'H');
            map.put(3, (byte) 'H');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scattering: 将数据写入到 buffer 时间，可以将 buffer 数据，依次写入 [分散]
     * Gathering: 从 buffer 读取数据时，可以采用 buffer 数组，依次读
     */
    public static void scatteringAndGathering() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);
        // 时间一个 buffer 数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 等待客户端连接 (telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        // 循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);
                Arrays.stream(byteBuffers)
                        .map(temp -> String.format("position: %d, limit: %d", temp.position(), temp.limit()))
                        .forEach(System.out::println);
            }
            // 将所有的 buffer 进行 flip
            Arrays.asList(byteBuffers).forEach(Buffer::flip);
            //将数据显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                //
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }
            // 将所有的 buffer 进行 clear
            Arrays.asList(byteBuffers).forEach(Buffer::clear);
            System.out.println("byteRead=" + byteRead + ",byteWrite=" + byteWrite + ",messageLength=" + messageLength);
        }
    }
}
