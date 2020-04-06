package com.yui.study.linkkafka.controller;

import com.yui.study.linkkafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XuZhuohao
 * @date 2020-04-06 15:58
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private KafkaService kafkaService;

    @GetMapping("send/{topic}/{message}")
    public String testSend(@PathVariable String topic, @PathVariable String message){
        kafkaService.sendMessage(topic, message);
        return "success";
    }
}
