package com.yui.study.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author XuZhuohao
 * @date 2019/3/27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {
    @Autowired
    private ZooKeeper zkClient;
    @Test
    public void t1(){
        System.out.println(zkClient.getState());
    }
}
