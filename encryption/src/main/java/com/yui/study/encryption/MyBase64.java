package com.yui.study.encryption;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

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
}
