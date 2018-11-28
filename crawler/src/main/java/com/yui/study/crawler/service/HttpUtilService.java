package com.yui.study.crawler.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * http的小工具
 *
 * @author XuZhuohao
 * @date 2018/11/27
 */
public interface HttpUtilService {
    /**
     * 根据正则表达式，从html中，下载图片
     * @param mainUrl 选择页面
     * @param pattern 正则表达式
     */
    File downloadImage(String mainUrl, String pattern);

    /**
     * 根据相同，相似，构建正则表达式，从html中，下载图片
     * @param mainUrl 选择页面
     * @param sameChars 相同字符串
     * @param likeChars 相似字符串
     */
    File downloadImage(String mainUrl, Map<Integer, String> sameChars, Map<Integer, String> likeChars);
    /**
     * 根据正则表达式，从html中，下载图片
     * @param mainUrl 选择页面
     * @param pattern 正则表达式
     * @param httpServletResponse response
     */
    void downloadImage(String mainUrl, String pattern, HttpServletResponse httpServletResponse);
    /**
     * 根据相同，相似，构建正则表达式，从html中，下载图片
     * @param mainUrl 选择页面
     * @param sameChars 相同字符串
     * @param likeChars 相似字符串
     * @param httpServletResponse response
     */
    void downloadImage(String mainUrl, Map<Integer, String> sameChars, Map<Integer, String> likeChars, HttpServletResponse httpServletResponse);

}
