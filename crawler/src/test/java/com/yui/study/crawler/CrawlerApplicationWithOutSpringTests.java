package com.yui.study.crawler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class CrawlerApplicationWithOutSpringTests {
    private Long startTime;
    @Before
    public void before(){
        System.out.println("测试开始=================");
        this.startTime = System.currentTimeMillis();
    }
    @After
    public void after(){
        System.out.println("测试结束=================");
        System.out.println("耗费时长:" + (System.currentTimeMillis() - this.startTime));
    }

    @Test
    public void contextLoads() {
    }

}
