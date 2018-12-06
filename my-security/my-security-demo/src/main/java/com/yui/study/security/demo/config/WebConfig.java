package com.yui.study.security.demo.config;

import com.yui.study.security.demo.filter.TimeFilter;
import com.yui.study.security.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * web 配置
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TimeInterceptor timeInterceptor;


    private ThreadPoolTaskExecutor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("ronnie-task-");
        executor.initialize();
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(getAsyncExecutor());
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(timeInterceptor);
    }

//    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter(){
        FilterRegistrationBean<TimeFilter> registrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>(16);
        urls.add("/user/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
