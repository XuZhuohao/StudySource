package com.yui.study.Cfg.controller;

import com.yui.study.Cfg.config.SystemConfigInner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test02")
public class HelloController02 {
    @Autowired
    private SystemConfigInner systemConfigInner;

    @GetMapping("/t1")
    public String test(){
        System.out.println("test : " + systemConfigInner.getUsername() + "\t" + systemConfigInner.getPassword());
        return "test";
    }

}
