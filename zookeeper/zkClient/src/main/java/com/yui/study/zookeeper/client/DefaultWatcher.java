package com.yui.study.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * 默认观察者
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
public class DefaultWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("default watcher");
    }
}
