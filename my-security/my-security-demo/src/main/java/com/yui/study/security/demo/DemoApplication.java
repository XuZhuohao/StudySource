package com.yui.study.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot 入口
 *
 * @author XuZhuohao
 * @date 2018-09-17 11:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yui.study.security.browser"})
//@EnableSwagger2
public class DemoApplication {
    /**
     * 启动入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
