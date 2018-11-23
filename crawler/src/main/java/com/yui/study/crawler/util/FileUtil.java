package com.yui.study.crawler.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 文件操作
 *
 * @author XuZhuohao
 * @date 2018/11/21
 */
public class FileUtil {
    /**
     * 保存上传的文件到临时目录
     * @param file 上传的MultipartFile文件
     * @param path 临时目录
     * @return
     */
    public static String saveMultipartFile(MultipartFile file, String path){
        // 1.保存文件保存目录的存在
        mkdirDirectory(path);
        // 2.生成保存文件的路径
        String filePath = path + File.separator + file.getOriginalFilename();
        // 3.保存文件到指定路径中
        File wordFile = new File(filePath);
        try(OutputStream os = new FileOutputStream(wordFile)){
            os.write(file.getBytes());
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("保存临时文件失败，" + filePath);
        }
        return wordFile.getAbsolutePath();
    }
    /**
     * 创建文件保存目录
     * @param path 路径
     */
    public static void mkdirDirectory(String path){
        File file = new File(path);
        if (path.contains(".") && !file.exists()){
            System.out.println("创建文件夹：" + file.getParentFile().getAbsolutePath() + " ，结果：" + file.getParentFile().mkdirs());
        } else if (!file.exists()){
            System.out.println("创建文件夹：" + file.getAbsolutePath() + " ，结果：" + file.mkdirs());
        }
    }
}
