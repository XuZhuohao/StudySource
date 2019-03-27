package com.yui.study.zookeeper.client;

import com.yui.study.zookeeper.config.ZkClientProperties;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * zookeeper初始化
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
@Configuration
public class InitZkClient {
    @Autowired
    private ZkClientProperties zkClientProperties;
    @Autowired
    private Watcher defaultWatcher;

    private static final Logger logger = LoggerFactory.getLogger(InitZkClient.class);

    @Bean(name = "zkClient")
    public ZooKeeper getZkClient(){
        if (zkClientProperties.getConnects() == null){
            throw new RuntimeException("zk.client.connects 配置不能为空");
        }
        logger.info("zookeeper 初始化：connect:[{}],SessionTimeout:{},watcher:{}",
                zkClientProperties.getConnects(),
                zkClientProperties.getSessionTimeout(),
                defaultWatcher.getClass());
        try {
            return new ZooKeeper(zkClientProperties.getConnects(), zkClientProperties.getSessionTimeout(), defaultWatcher);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("zookeeper 初始化失败",e.getCause());
        }
    }
}
