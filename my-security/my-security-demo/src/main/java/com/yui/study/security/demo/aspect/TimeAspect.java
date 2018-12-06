package com.yui.study.security.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Aspect
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
//@Aspect
//@Component
public class TimeAspect {
    @Transactional
    @Around("execution(* com.yui.study.security.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect begin");
        long start = System.currentTimeMillis();
        // 获取参数
        for (Object arg : pjp.getArgs()) {
            System.out.println("arg is " + arg);
        }
        Object o = pjp.proceed();
        System.out.println("aspect end ：o =" + o.getClass().getName());
        System.out.println("aspect 耗时："+(System.currentTimeMillis() - start));
        return o;
    }
}
