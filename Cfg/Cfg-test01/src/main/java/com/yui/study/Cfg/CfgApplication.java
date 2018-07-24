package com.yui.study.Cfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages="")
public class CfgApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfgApplication.class, args);
	}
}
