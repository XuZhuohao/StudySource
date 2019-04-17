package com.yui.study.spring.base.resolvable;

import com.alibaba.fastjson.JSON;
import com.yui.study.spring.base.dao.Foo;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;

/**
 * @author XuZhuohao
 * @date 2019/4/17
 */
public class ResolvableTypeTest {
    public static void main(String[] args) throws Exception {
        t1();
    }

    private static void t1() throws Exception {
        ResolvableType resolvableType = ResolvableType.forClass(Foo.class);
        System.out.println(resolvableType.getType());
        System.out.println(resolvableType.getSuperType().getType());

        final Field t1 = Foo.class.getDeclaredField("upperBound");
        resolvableType = ResolvableType.forField(t1);
        System.out.println(resolvableType.getType());
        System.out.println(resolvableType.getSuperType().getType());
        System.out.println(resolvableType.getRawClass());

        resolvableType = ResolvableType.forClass(Foo.class);
        System.out.println(resolvableType.isAssignableFrom(MyFoo.class));

        resolvableType = ResolvableType.forClass(MyFoo.class);
        System.out.println(resolvableType.isAssignableFrom(Foo.class));
    }


    private class MyFoo extends Foo<Integer> {
    }
}
