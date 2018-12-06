package com.yui.study.security.browser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * browser 安全验证
 *
 * @author XuZhuohao
 * @date 2018-12-06 21:21
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.formLogin()
                .and()
                // request 验证
                .authorizeRequests()
                // 任何请求
                .anyRequest()
                .authenticated();
    }
}
