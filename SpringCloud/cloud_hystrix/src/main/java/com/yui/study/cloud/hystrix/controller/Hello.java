package com.yui.study.cloud.hystrix.controller;

import com.yui.study.cloud.hystrix.feign.HelloTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign测试
 *
 * @author XuZhuohao
 * @date 2018/8/28
 */
@RestController
public class Hello {
    @Autowired
    HelloTest helloTest;

    @RequestMapping("/hello")
    public String hello() {
        return helloTest.test() + " \t test \t" + helloTest.test01();
    }
}
