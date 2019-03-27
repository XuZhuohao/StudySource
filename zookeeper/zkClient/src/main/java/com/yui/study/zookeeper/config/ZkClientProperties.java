package com.yui.study.zookeeper.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * zk 客户端参数配置
 *
 * @author XuZhuohao
 * @date 2019/3/27
 */
@ConfigurationProperties(
        prefix = "zk.client"
)
@Component
public class ZkClientProperties {
    private String connects;
    private int sessionTimeout = 10*1000;

    public String getConnects() {
        return connects;
    }

    public void setConnects(String connects) {
        this.connects = connects;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
