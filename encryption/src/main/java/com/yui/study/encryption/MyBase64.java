package com.yui.study.encryption;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * MyBase64
 *
 * @author XuZhuohao
 * @date 2018/9/10
 */
public class MyBase64 {
    public static void main(String[] args) throws IOException {
        String src = "Xu Zhuo hao";
        System.out.println("src : " + src);
        System.out.println("JDK:--------");
        String base64Src = jdkBase64Encode(src);
        System.out.println("Encode:\t" + base64Src);
        System.out.println("Decode:\t" + jdkBase64Decode(base64Src));

        System.out.println("Commons Codec:--------");
        base64Src = ccBase64Encode(src);
        System.out.println("Encode:\t" + base64Src);
        System.out.println("Decode:\t" + ccBase64Decode(base64Src));

        System.out.println("Bouncy Castle:--------");
        base64Src = bcBase64Encode(src);
        System.out.println("Encode:\t" + base64Src);
        System.out.println("Decode:\t" + bcBase64Decode(base64Src));

        System.out.println("test------------------------");
        test1(src);
    }

    public static String jdkBase64Encode(String src) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes());
    }

    public static String jdkBase64Decode(String src) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(src));
    }

    public static String ccBase64Encode(String src) {
        return new String(Base64.encodeBase64(src.getBytes()));
    }

    public static String ccBase64Decode(String src) {
        return new String(Base64.decodeBase64(src.getBytes()));
    }

    public static String bcBase64Encode(String src) {
        return new String(org.bouncycastle.util.encoders.Base64.encode(src.getBytes()));
    }

    public static String bcBase64Decode(String src) {
        return new String(org.bouncycastle.util.encoders.Base64.decode(src.getBytes()));
    }

    @Deprecated
    public static void test1(String src) {
        byte[] srcBtyes = src.getBytes();
        for (byte b : srcBtyes) {
            System.out.print(b + " ");
        }
        System.out.println();
        for (byte b : srcBtyes) {
            System.out.print(Integer.toBinaryString(b) + " ");
        }
        System.out.println();
        StringBuilder allByte = new StringBuilder();
        for (byte b : srcBtyes) {
            DecimalFormat g1 = new DecimalFormat("00000000");
            System.out.print(g1.format(Integer.valueOf(Integer.toBinaryString(b))) + " ");
            allByte.append(g1.format(Integer.valueOf(Integer.toBinaryString(b))));
        }
        int length = allByte.toString().length();
        for (int i = 0; i < 6 - length % 6; i++) {
            allByte.append(0);
        }
        System.out.println();
        for (String temp : allByte.toString().split("")) {
            System.out.print(temp);
        }
        System.out.println();
        String[] allBytes = allByte.toString().split("");
        StringBuilder rtnCode = new StringBuilder();

        System.out.println();
        for (int i = 0; i < allBytes.length - allBytes.length % 6; i = i + 6) {
            System.out.print(" ");
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                System.out.print(allBytes[i + j]);
                stringBuilder.append(allBytes[i + j]);
            }
            rtnCode.append(Base64Code.COD.get(Integer.valueOf(stringBuilder.toString(), 2)));
        }
        /*
            TODO： 错了
            (allBytes.length % 24) % 8
            每6个对应一个code
            每8个为一个byte
            每24个为一组
            所以一组有3个byte,最后一组中每少一个 byte，加一个 =
            code:  010110 000111 010100 100000 010110 100110 100001 110101 011011 110010 000001 101000 011000 010110 111100
            byte:  01011000 01110101 00100000 01011010 01101000 01110101 01101111 00100000 01101000 01100001 01101111
            group: 01011000 01110101 00100000 | 01011010 01101000 01110101 | 01101111 00100000 01101000 | 01100001 01101111
         */
        for (int i = 0; i < 4 - (24 - (allBytes.length % 24)) / 6; i++) {
            rtnCode.append("=");
        }
        System.out.println();
        System.out.println(rtnCode.toString());
        System.out.println("WHUgWmh1byBoYW8=");
//        Integer.valueOf()

    }
}
