package com.yui.study.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloContrller {
    @Value("${test.t2}")
    String t2;

    @GetMapping("testHello")
    public String testHello() {
        return "hello:" + t2;
    }
}
