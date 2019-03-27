package com.yui.study.zookeeper.client;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 测试client
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("10.1.161.90:2181,10.1.161.89,10.1.161.185", 10 * 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watcherEvent");
            }
        });
        zk.create("/test/t1", "测试1".getBytes(),  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.getData("/test/t1", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watcherEvent02");
            }
        }, null);
        zk.setData("/test/t1","测试2".getBytes(), -1);
        Thread.sleep(10*1000);
    }
}
