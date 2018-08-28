package com.yui.study.cloud.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign测试 接口
 *
 * @author XuZhuohao
 * @date 2018/8/28
 */
@FeignClient(name = "service-client")
public interface HelloTest {
    @GetMapping(value = "test")
    String test();
}
