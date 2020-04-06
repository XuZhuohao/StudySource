package com.yui.study.linkkafka.service;

/**
 * @author XuZhuohao
 * @date 2020-04-06 15:45
 */
public interface KafkaService {
    /**
     * 消息发送
     * @param topic 主题
     * @param message 消息
     */
    void sendMessage(String topic, String message);

    /**
     * 接受消息
     * @param message 接收消息
     */
    void recoverMessage(String message);

}
