package com.yui.study.security.browser.config;

import com.yui.study.security.browser.encoder.MyPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author XuZhuohao
 * @date 2018/12/10
 */
@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }
}
