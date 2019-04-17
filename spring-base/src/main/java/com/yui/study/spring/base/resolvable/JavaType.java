package com.yui.study.spring.base.resolvable;


import com.yui.study.spring.base.dao.Foo;

import java.lang.reflect.*;

/**
 * java type 理解
 *
 * @author XuZhuohao
 * @date 2019/4/17
 */
public class JavaType {
    public static void main(String[] args) throws Exception {
        // class 实现了 Type 接口
        /// test
//        Type type = Integer.class;
//        System.out.println(type);
//        parameterizedType();
//        typeVariable();
//        wildcardType();
        genericArrayType();
    }

    /**
     * 输出 T[] 去除 [] 后的类型
     *
     * @throws NoSuchFieldException
     */
    private static void genericArrayType() throws NoSuchFieldException {
        final Field field = Foo.class.getDeclaredField("pTypeArray");
        final GenericArrayType genericType = (GenericArrayType) field.getGenericType();
        System.out.println(genericType.getGenericComponentType());
    }

    /**
     * 获取泛型的上界下界
     *
     * @throws Exception
     */
    private static void wildcardType() throws Exception {
        class WildcardTypeTest {
            private void t1(String fieldName) throws Exception {
                final Field field = Foo.class.getDeclaredField(fieldName);
                final ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                final Type[] actualTypeArguments = genericType.getActualTypeArguments();
                final WildcardType actualTypeArgument = (WildcardType) actualTypeArguments[0];
                for (Type upperBound : actualTypeArgument.getUpperBounds()) {
                    System.out.println(upperBound);
                }
                display("-");
                for (Type lowerBound : actualTypeArgument.getLowerBounds()) {
                    System.out.println(lowerBound);
                }
            }
        }
        final WildcardTypeTest wildcardTypeTest = new WildcardTypeTest();
        display("upperBound");
        wildcardTypeTest.t1("upperBound");
        display("lowerBound");
        wildcardTypeTest.t1("lowerBound");

    }

    /**
     * 处理泛型中泛型属性
     *
     * @throws Exception
     */
    private static void typeVariable() throws Exception {
        final Field t = Foo.class.getDeclaredField("t1");
        TypeVariable typeVariable = (TypeVariable) t.getGenericType();
        display("getAnnotatedBounds");
        for (AnnotatedType annotatedBound : typeVariable.getAnnotatedBounds()) {
            System.out.println(annotatedBound.getType());
        }
        display("getBounds");
        // <T extends ? & ?> 输出 ?
        for (Type bound : typeVariable.getBounds()) {
            System.out.println(bound);
        }
        display("getGenericDeclaration");
        // 输出 com.yui.study.spring.base.dao.Foo
        System.out.println(typeVariable.getGenericDeclaration());
        display("getName");
        // 输出 T
        System.out.println(typeVariable.getName());
    }

    /**
     * 处理泛型<> 中的类型
     */
    private static void parameterizedType() {
        // 创建匿名子类
        Foo<Integer> foo = new Foo<Integer>() {
        };
        Type mySuperClass = foo.getClass().getGenericSuperclass();
        final ParameterizedType parameterizedType = (ParameterizedType) mySuperClass;
        // 输出 java.lang.Integer
        for (Type tempType : parameterizedType.getActualTypeArguments()) {
            System.out.println(tempType);
        }
        // 输出 com.yui.study.spring.base.dao.Foo
        System.out.println(parameterizedType.getRawType());
        // 输出 null，属于nested类型,使用场景比较少
        System.out.println(parameterizedType.getOwnerType());
    }

    public static void display(String tag) {
        System.out.println("-------------" + tag + "-------------");
    }
}
