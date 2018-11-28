package com.yui.study.crawler.service.impl;

import com.yui.study.crawler.CrawlerApplicationTests;
import com.yui.study.crawler.CrawlerApplicationWithOutSpringTests;
import com.yui.study.crawler.service.HttpUtilService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class HttpUtilServiceImplTest extends CrawlerApplicationWithOutSpringTests {

    HttpUtilService httpUtilService = new HttpUtilServiceImpl();
    @Test
    public void downloadImage() {
        // String html, String pattern
        String url = "http://www.nipic.com/";
        // <img src="http://icon.nipic.com/BannerPic/20181126/original/20181126100510_1.jpg">
        String pattern = "<img src=\"([^\"]*)\"";
        httpUtilService.downloadImage(url, pattern);
    }

}