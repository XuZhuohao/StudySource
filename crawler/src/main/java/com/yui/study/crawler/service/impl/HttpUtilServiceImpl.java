package com.yui.study.crawler.service.impl;

import com.yui.study.crawler.service.HttpUtilService;
import com.yui.study.crawler.util.FileUtil;
import com.yui.study.crawler.util.PatternUtil;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * http 工具实现
 *
 * @author XuZhuohao
 * @date 2018/11/27
 */
public class HttpUtilServiceImpl extends BaseHttpService implements HttpUtilService {
    @Override
    public File downloadImage(String html, String pattern) {
        List<String> urls = PatternUtil.getStringByPattern(pattern, html, 1);
        String path = "temp" + File.separator + System.currentTimeMillis();
        // 创建文件夹
        FileUtil.mkdirDirectory(path);
        urls.forEach(url -> this.downloadImage(url, path, ""));
        // 压缩文件


    }

    @Override
    public File downloadImage(String html, Map<Integer, String> sameChars, Map<Integer, String> likeChars) {

    }

    @Override
    public void downloadImage(String html, String pattern, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void downloadImage(String html, Map<Integer, String> sameChars, Map<Integer, String> likeChars, HttpServletResponse httpServletResponse) {

    }

    private void downloadImage(String url, String path, String fileName) {
        // 请求图片
        this.doGet(url, null, null);
        // 保存图片 "(([^/|^\\\\]*)[\\.]{1}([\\w]*))"
        String pattern = "([^/|^\\\\]*)[\\.]{1}([\\w]*)";
        if (fileName == null || fileName.length() == 0) {
            fileName = PatternUtil.getStringByPattern(pattern, url, 0).get(0);
        }
        try(FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path + File.separator + fileName))){
            // 往文件里面写数据
            byte[] buffer = new byte[1024];
            int length;
            while((length = this.getIs().read(buffer))>0){
                imageOutput.write(buffer,0,length);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
