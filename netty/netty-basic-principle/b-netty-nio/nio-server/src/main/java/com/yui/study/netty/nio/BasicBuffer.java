package com.yui.study.netty.nio;

import java.nio.IntBuffer;

/**
 * @author XuZhuohao
 * @date 2019-11-20 22:08
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个buffer,大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 从 buffer 读取数据
        // 将 buffer 转换，读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
