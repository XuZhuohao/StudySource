package com.yui.study.spring.base.resolvable;

import com.yui.study.spring.base.dao.Foo;
import org.springframework.core.MethodParameter;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2019/4/17
 */
public class JavaTypeGet {
    public static void main(String[] args) throws Exception {
        ///
//        useClass();
//        useField();
//        useMethod();
        useMethodParameter();
    }

    /**
     * 通过 org.springframework.core.MethodParameter 获取
     * @throws NoSuchMethodException
     */
    private static void useMethodParameter() throws Exception {
        final Method method = Foo.class.getMethod("setLowerBound", List.class);
        final MethodParameter methodParameter = MethodParameter.forExecutable((Executable) method, 0);
        System.out.println(methodParameter.getParameterType());
        System.out.println(methodParameter.getGenericParameterType());
    }

    /**
     * 通过 Method 来获取 type
     *
     * @throws NoSuchMethodException
     */
    private static void useMethod() throws NoSuchMethodException {
        /*
        interface java.util.List
        ----------------
        java.util.List<? super java.lang.String>
        ----------------
        interface java.util.List
        ----------------
        java.util.List<? extends java.lang.Integer>
         */
        final Method method = Foo.class.getMethod("setLowerBound", List.class);
        for (Class<?> typeParameter : method.getParameterTypes()) {
            System.out.println(typeParameter);
        }
        System.out.println("----------------");
        for (Type genericParameterType : method.getGenericParameterTypes()) {
            System.out.println(genericParameterType);
        }
        System.out.println("----------------");
        final Constructor<Foo> constructor = Foo.class.getConstructor(List.class);
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            System.out.println(parameterType);
        }
        System.out.println("----------------");
        for (Type genericParameterType : constructor.getGenericParameterTypes()) {
            System.out.println(genericParameterType);
        }
    }

    /**
     * 通过 Field 来获取 type
     *
     * @throws NoSuchFieldException
     */
    private static void useField() throws NoSuchFieldException {
        final Field t1 = Foo.class.getDeclaredField("upperBound");
        final Type genericType = t1.getGenericType();
        final Class<?> type = t1.getType();
        // 输出 java.util.List<? extends java.lang.Integer>
        System.out.println(genericType);
        // 输出 interface java.util.List
        System.out.println(type);
    }

    /**
     * 通过 class 来获取 type
     * getGenericSuperclass()
     * getSuperclass()
     * 使用泛型时两者有区别，否则一样
     */
    private static void useClass() {
        final Type genericSuperclass = MyFoo.class.getGenericSuperclass();
        final Class<? super MyFoo> superclass = MyFoo.class.getSuperclass();
        // 输出 com.yui.study.spring.base.dao.Foo<java.lang.Integer>
        System.out.println(genericSuperclass);
        // 输出 class com.yui.study.spring.base.dao.Foo
        System.out.println(superclass);
    }


    private class MyFoo extends Foo<Integer> {
    }
}
