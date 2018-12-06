package com.yui.study.security.demo.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 调用控制器之前
        System.out.println("preHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 调用控制器正常返回之后(异常时不调用)
        System.out.println("postHandle");
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("postHandle Interceptor 耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 调用控制器返回之后
        System.out.println("afterCompletion");
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("afterCompletion Interceptor 耗时：" + (System.currentTimeMillis() - startTime));
    }
}
