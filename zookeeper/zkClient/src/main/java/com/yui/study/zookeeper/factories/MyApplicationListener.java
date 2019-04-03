package com.yui.study.zookeeper.factories;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * ApplicationStartingEvent
 *
 * @author XuZhuohao
 * @date 2019/4/3
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
//        applicationEvent
    }
}
