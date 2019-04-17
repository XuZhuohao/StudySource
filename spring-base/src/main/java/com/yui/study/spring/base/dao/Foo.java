package com.yui.study.spring.base.dao;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2019/4/17
 */
@Setter
@Getter
public class Foo<T extends Number & Serializable> {
    public Foo() {
    }

    public Foo(List<? extends Integer> upperBound) {
        this.upperBound = upperBound;
    }

    T t1;
    List<? extends Integer> upperBound;
    List<? super String> lowerBound;
    T[] t2;
    List<String>[] pTypeArray;
}
