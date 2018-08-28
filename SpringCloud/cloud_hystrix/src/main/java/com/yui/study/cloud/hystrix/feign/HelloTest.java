package com.yui.study.cloud.hystrix.feign;

import com.yui.study.cloud.hystrix.fallback.HelloTestHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign测试 接口
 *
 * @author XuZhuohao
 * @date 2018/8/28
 */
@FeignClient(name = "service-client", fallback = com.yui.study.cloud.hystrix.fallback.HelloTestHystrix.class)
public interface HelloTest {
    @GetMapping(value = "null")
    String test();

    @GetMapping(value = "test")
    String test01();
}
