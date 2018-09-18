package com.yui.study.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World
 *
 * @author XuZhuohao
 * @date 2018-09-17 16:29
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
