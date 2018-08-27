package com.yui.study.cloud.ribbon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHello {

    @GetMapping("test")
    public String test() {
        return "this is test spring cloud ribbon";
    }
}
