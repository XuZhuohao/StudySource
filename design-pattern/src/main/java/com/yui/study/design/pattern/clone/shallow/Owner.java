package com.yui.study.design.pattern.clone.shallow;

/**
 * 所有者
 *
 * @author XuZhuohao
 * @date 2019/12/25
 */
public class Owner {
    private String name;

    public String getName() {
        return name;
    }

    public Owner setName(String name) {
        this.name = name;
        return this;
    }
}
