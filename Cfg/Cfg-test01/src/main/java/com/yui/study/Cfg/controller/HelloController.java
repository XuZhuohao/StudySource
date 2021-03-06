package com.yui.study.Cfg.controller;

import com.yui.study.Cfg.config.*;
import com.yui.study.Cfg.test.config.SystemConfigInnerIn;
import com.yui.study.Cfg.test.config.SystemConfigInnerIn06;
import com.yui.study.Cfg.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private SystemConfig systemConfig;
    /*@Autowired
    private SystemConfig01 systemConfig01;*/
    @Autowired
    private SystemConfigInner systemConfigInner;
    @Autowired
    private SystemConfig02 systemConfig02;
    @Autowired
    private SystemConfig03 systemConfig03;
    @Autowired
    private SystemConfig04 systemConfig04;
    @Autowired
    private SystemConfig05 systemConfig05;
    @Autowired
    private SystemConfig06 systemConfig06;
    @Autowired
    private SystemConfigInner06 systemConfigInner06;
    @Autowired
    private SystemConfigInnerIn systemConfigInnerIn;
    @Autowired
    private SystemConfigInnerIn06 systemConfigInnerIn06;
    @ResponseBody
    @GetMapping("/t1")
    public String test(){
        System.out.println("-----------------------t1:");
        // 测试本模块 @ConfigurationProperties(prefix="syspara")
        System.out.println("systemConfig : " + systemConfig.getUsername() + "\t" + systemConfig.getPassword());
        // 测试本模块 @ConfigurationProperties(prefix="syspara")
        //System.out.println("systemConfig01 : " + systemConfig01.getUsername() + "\t" + systemConfig01.getPassword());
        // 测试底层模块 @ConfigurationProperties(prefix="syspara")
        System.out.println("systemConfigInner : " + systemConfigInner.getUsername() + "\t" + systemConfigInner.getPassword());
        // @ConfigurationProperties(prefix="syspara")
        System.out.println("systemConfig02 : " + systemConfig02.getUsername() + "\t" + systemConfig02.getPassword());
        System.out.println("systemConfig03 : " + systemConfig03.getUsername() + "\t" + systemConfig03.getPassword());
        System.out.println("systemConfig04 : " + systemConfig04.getUsername() + "\t" + systemConfig04.getPassword());
        System.out.println("systemConfig05 : " + systemConfig05.getUsername() + "\t" + systemConfig05.getPassword());
        System.out.println("systemConfig06 : " + systemConfig06.getUsername() + "\t" + systemConfig06.getPassword());
        System.out.println("systemConfigInner06 : " + systemConfigInner06.getUsername() + "\t" + systemConfigInner06.getPassword());
        System.out.println("systemConfigInnerIn : " + systemConfigInnerIn.getUsername() + "\t" + systemConfigInnerIn.getPassword());
        System.out.println("systemConfigInnerIn06 : " + systemConfigInnerIn06.getUsername() + "\t" + systemConfigInnerIn06.getPassword());
        return "test";
    }
    @ResponseBody
    @GetMapping("/t2")
    public String test01(){
        System.out.println("-----------------------t2:");
        ConfigUtil  cfu = new ConfigUtil();
        cfu.displayAllCfg();
        return "1";
    }

}
