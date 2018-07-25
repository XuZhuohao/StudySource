package com.yui.study.Cfg.util;

import com.yui.study.Cfg.config.*;
import com.yui.study.Cfg.test.config.SystemConfigInnerIn;
import com.yui.study.Cfg.test.config.SystemConfigInnerIn06;

public class ConfigUtil {
    private SystemConfig systemConfig;
    private SystemConfigInner systemConfigInner;
    private SystemConfig02 systemConfig02;
    private SystemConfig03 systemConfig03;
    private SystemConfig04 systemConfig04;
    private SystemConfig05 systemConfig05;
    private SystemConfig06 systemConfig06;
    private SystemConfigInner06 systemConfigInner06;
    private SystemConfigInnerIn systemConfigInnerIn;
    private SystemConfigInnerIn06 systemConfigInnerIn06;
    public ConfigUtil(){
        this.systemConfig = (SystemConfig) ApplicationContextRegister.getApplicationContext().getBean("systemConfig");
        this.systemConfigInner = (SystemConfigInner) ApplicationContextRegister.getApplicationContext().getBean("systemConfigInner");
        this.systemConfig02 = (SystemConfig02) ApplicationContextRegister.getApplicationContext().getBean("systemConfig02");
        this.systemConfig03 = (SystemConfig03) ApplicationContextRegister.getApplicationContext().getBean("systemConfig03");
        this.systemConfig04 = (SystemConfig04) ApplicationContextRegister.getApplicationContext().getBean("systemConfig04");
        this.systemConfig05 = (SystemConfig05) ApplicationContextRegister.getApplicationContext().getBean("systemConfig05");
        this.systemConfig06 = (SystemConfig06) ApplicationContextRegister.getApplicationContext().getBean("systemConfig06");
        this.systemConfigInner06 = (SystemConfigInner06) ApplicationContextRegister.getApplicationContext().getBean("systemConfigInner06");
        this.systemConfigInnerIn = (SystemConfigInnerIn) ApplicationContextRegister.getApplicationContext().getBean("systemConfigInnerIn");
        this.systemConfigInnerIn06 = (SystemConfigInnerIn06) ApplicationContextRegister.getApplicationContext().getBean("systemConfigInnerIn06");
    }
    public void displayAllCfg(){
        // 测试本模块 @ConfigurationProperties(prefix="syspara")
        System.out.println("systemConfig : " + systemConfig.getUsername() + "\t" + systemConfig.getPassword());
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
    }

}
