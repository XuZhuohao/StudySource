package com.yui.study.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author XuZhuohao
 * @date 2020/8/5 16:17
 */
public class MyProperty {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\Projects\\StudySource\\jdk-study\\src\\main\\java\\com\\yui\\study\\property\\main.properties"));
        String test01 = System.getProperty("test01");
        System.out.println(test01);
    }
}
