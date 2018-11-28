package com.yui.study.crawler.service.impl;

import com.alibaba.fastjson.JSON;
import com.yui.study.crawler.service.HttpUtilService;
import com.yui.study.crawler.util.FileUtil;
import com.yui.study.crawler.util.PatternUtil;
import org.springframework.stereotype.Service;

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
@Service
public class HttpUtilServiceImpl extends BaseHttpService implements HttpUtilService {
    @Override
    public File downloadImage(String mainUrl, String pattern) {
        this.doGet(mainUrl, null, null);
        String html = this.getResult();
        List<String> urls = PatternUtil.getStringByPattern(pattern, html, 1);
        System.out.println(JSON.toJSONString(urls));
        String path = "temp" + File.separator + System.currentTimeMillis();
        // 创建文件夹
        FileUtil.mkdirDirectory(path);
        urls.forEach(url -> this.downloadImage(url, path, ""));
        // 压缩文件
        return FileUtil.zipFiles(path, false, true);
    }

    @Override
    public File downloadImage(String mainUrl, Map<Integer, String> sameChars, Map<Integer, String> likeChars) {
        return null;
    }

    @Override
    public void downloadImage(String mainUrl, String pattern, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void downloadImage(String mainUrl, Map<Integer, String> sameChars, Map<Integer, String> likeChars, HttpServletResponse httpServletResponse) {

    }

    private void downloadImage(String url, String path, String fileName) {
        // 请求图片
        this.doGet(url, null, null);
        // 保存图片 "(([^/|^\\\\]*)[\\.]{1}([\\w]*))"
        String pattern = "(([^/|^\\\\]*)[\\.]{1}([\\w]*))";
        if (fileName == null || fileName.length() == 0) {
            final List<String> fileNames = PatternUtil.getStringByPattern(pattern, url, 1);
            fileName = fileNames.get(fileNames.size() - 1);
        }
        try (FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path + File.separator + fileName))) {
            // 往文件里面写数据
            imageOutput.write(this.getBao().toByteArray());
            imageOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
