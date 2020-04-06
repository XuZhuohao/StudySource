package com.yui.study.linkkafka.service.impl;

import com.yui.study.linkkafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author XuZhuohao
 * @date 2020-04-06 15:46
 */
@Service
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @Override
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "test", topics = "test_xzh")
    @KafkaListener(topics = "test_xzh01")
    public void recoverMessage(String message) {
        System.out.println(message);

    }
}
