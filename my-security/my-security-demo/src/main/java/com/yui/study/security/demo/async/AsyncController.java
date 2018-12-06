package com.yui.study.security.demo.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * 异步控制器
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
@RestController
public class AsyncController {
    private Logger logger =  LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public String test() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(10000);
        logger.info("主线程结束");
        return "SUCCESS";
    }
    @RequestMapping("/orderAsync")
    public Callable<String> test02() {
        logger.info("主线程开始");
        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(10000);
            logger.info("副线程结束");
            return "SUCCESS";
        };
        logger.info("主线程结束");
        return result;

    }

    @RequestMapping("/orderDeferredResult")
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始");

        String orderNumber = String.valueOf((Math.random()*9+1)*100000);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        return result;

    }
}
