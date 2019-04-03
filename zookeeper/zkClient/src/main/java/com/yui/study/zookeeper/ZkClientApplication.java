package com.yui.study.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.yui.study.zookeeper")
public class ZkClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkClientApplication.class, args);
    }

}
