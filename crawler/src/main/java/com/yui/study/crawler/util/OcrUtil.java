package com.yui.study.crawler.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import java.io.*;

/**
 * 图像识别
 *
 * @author XuZhuohao
 * @date 2018/11/23
 */
public class OcrUtil {
    public static void main(String[] args) {
        System.out.println(getImgContent("C:\\Users\\Administrator\\Desktop\\valiCodeImg.jpeg"));
    }
    public static void downloadJPG(InputStream input, String fileName) throws IOException {
//        InputStream input = httpResponse.getEntity().getContent()
        OutputStream output = new FileOutputStream(new File(fileName));
        IOUtils.copy(input, output);
        if (output != null) {
            output.close();
        }
        output.flush();
    }

    public static String getImgContent(String imgUrl) {
        String content = "";
        File imageFile = new File(imgUrl);
        //读取图片数字
        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        //英文库识别数字比较准确
        instance.setLanguage("eng");
        instance.setDatapath(tessDataFolder.getAbsolutePath());

        try {
            content = instance.doOCR(imageFile).replace("\n", "");
            System.out.println(content);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return content;
    }
}
