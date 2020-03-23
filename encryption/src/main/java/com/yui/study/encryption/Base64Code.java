package com.yui.study.encryption;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Base64 对应编码
 *
 * @author XuZhuohao
 * @date 2018/9/10
 */
public class Base64Code {
    public final static List<String> COD = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "/"
    );

    public static void main(String[] args) throws IOException {
        /*
         * 1.获取每个字符的 ASCII
         * 2.把ASCII转成二进制(8位，不足8位，前面补0,8位其实就是一个byte)，按顺序放好
         * 3.每6个值作为一个看做一个code，最后不足6个值的，后面补0
         * 4.按照code查找 对应表 。转成编码后字符
         * 5.每4个code为一个组，最后一组缺少多少个code，补多少个"="
         */
        String src = "binary\\x00string";
        final byte[] bytes = src.getBytes();
        StringBuilder binaryBuilder = new StringBuilder();
        for (byte temp : bytes) {
            //获取每个字符的 ASCII
            //把ASCII转成二进制(8位，不足8位，前面补0,8位其实就是一个byte)，按顺序放好
            binaryBuilder.append(String.format("%08d",Integer.parseInt(Integer.toBinaryString(temp))));
        }
        // 每6个值作为一个看做一个code，最后不足6个值的，后面补0
        int fillZeroCnt = 6 - (binaryBuilder.length() % 6);
        int equalCnt = 4 - (binaryBuilder.length()/6 + 1) % 4;
        while(fillZeroCnt-- > 0){
            binaryBuilder.append("0");
        }
        final String binary = binaryBuilder.toString();
        StringBuilder result = new StringBuilder();
        // 按照code查找 对应表 。转成编码后字符
        for (int i = 0; i < binary.length(); i += 6) {
            final String code = binary.substring(i, i + 6);
            final int index = Integer.parseInt(code, 2);
            result.append(Base64Code.COD.get(index));
        }
        // TODO ====
        for(int i=0; i<equalCnt; i++){
            result.append("=");
        }
        System.out.println(result.toString());
        System.out.println(equalCnt);
        System.out.println(MyBase64.jdkBase64Encode(src));
//        System.out.println(new String(MyBase64.jdkBase64Decode(src).getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8));


    }
}
