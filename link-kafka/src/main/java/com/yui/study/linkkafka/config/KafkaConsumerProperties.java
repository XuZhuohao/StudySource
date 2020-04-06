package com.yui.study.linkkafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author XuZhuohao
 * @date 2020-04-06 14:48
 */
@Data
@Configuration
@ConfigurationProperties("yui.mq.kafka.consumer")
public class KafkaConsumerProperties {
    private Map<String, String> other;
    private String clusters;
    private String groupId;
    private boolean enableAutoCommit = true;
    private String sessionTimeoutMs = "120000";
    private String requestTimeoutMs = "160000";
    private String fetchMaxWaitMs = "5000";
    private String autoOffsetReset = "earliest";
    private String maxPollRecords = "10";
    private String maxPartitionFetch = "5048576";
    private String keyDeserializerClass = "org.apache.kafka.common.serialization.StringDeserializer";
    private String valueDeserializerClass = "org.apache.kafka.common.serialization.StringDeserializer";



}
