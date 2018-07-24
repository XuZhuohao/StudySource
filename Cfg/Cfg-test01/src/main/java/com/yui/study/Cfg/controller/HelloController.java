package com.yui.study.Cfg.controller;

import com.yui.study.Cfg.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private SystemConfigInner systemConfigInner;
    @Autowired
    private SystemConfig02 systemConfig02;
    @Autowired
    private SystemConfig03 systemConfig03;
    @Autowired
    private SystemConfig04 systemConfig04;
    @Autowired
    private SystemConfig04 systemConfig05;
    @GetMapping("/t1")
    public String test(){
        // 测试本模块 @ConfigurationProperties(prefix="syspara")
        System.out.println("test : " + systemConfig.getUsername() + "\t" + systemConfig.getPassword());
        // 测试底层模块 @ConfigurationProperties(prefix="syspara")
        System.out.println("test : " + systemConfigInner.getUsername() + "\t" + systemConfigInner.getPassword());
        // @ConfigurationProperties(prefix="syspara")
        System.out.println("test : " + systemConfig02.getUsername() + "\t" + systemConfig02.getPassword());
        System.out.println("test : " + systemConfig03.getUsername() + "\t" + systemConfig03.getPassword());
        System.out.println("test : " + systemConfig04.getUsername() + "\t" + systemConfig04.getPassword());
        System.out.println("test : " + systemConfig05.getUsername() + "\t" + systemConfig05.getPassword());
        return "test";
    }

}
