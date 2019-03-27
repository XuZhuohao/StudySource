package com.yui.study.zookeeper.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

/**
 * 观察者测试
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
@Component
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("my Watcher");
    }
}
