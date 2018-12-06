package com.yui.study.security.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 时间过滤器
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.printf("time filter 耗时：%d \r\n", System.currentTimeMillis() - startTime);
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
