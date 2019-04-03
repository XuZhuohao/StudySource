package com.yui.study.zookeeper.config;

import com.yui.study.zookeeper.client.DefaultWatcher;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 初始化 bean
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
public class InitBean {
    @Bean
    @ConditionalOnMissingBean(Watcher.class)
    public Watcher getDefaultWatcher(){
        return new DefaultWatcher();
    }
}
