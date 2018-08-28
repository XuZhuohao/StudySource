package com.yui.study.cloud.hystrix.fallback;

import com.yui.study.cloud.hystrix.feign.HelloTest;
import org.springframework.stereotype.Component;

/**
 * 熔断回调
 *
 * @author XuZhuohao
 * @date 2018/8/28
 */
@Component
public class HelloTestHystrix implements HelloTest {
    @Override
    public String test() {
        return "this is the hystrix tgest !!";
    }

    @Override
    public String test01() {
        return "this is the hystrix test02";
    }
}
