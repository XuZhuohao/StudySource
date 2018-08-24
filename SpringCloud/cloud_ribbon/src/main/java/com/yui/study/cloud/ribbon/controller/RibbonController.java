package com.yui.study.cloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xzh
 */
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("testRibbon")
    @LoadBalanced
    public String ribbonMethod(){
        String test = restTemplate.getForObject("http://service-helloworld/test", String.class);
        test += restTemplate.getForObject("http://config-client/testHello", String.class);
        return "xx:" + test;
    }
}
