package com.yui.study.zookeeper.config;

import com.yui.study.zookeeper.client.DefaultWatcher;
import org.apache.zookeeper.Watcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化 bean
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
@Configuration
public class InitBean {
    @Bean
    @ConditionalOnMissingBean(Watcher.class)
    public Watcher getDefaultWatcher(){
        return new DefaultWatcher();
    }

}
