package com.yui.study.h2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 测试entity
 *
 * @author XuZhuohao
 * @date 2018/12/4
 */
@Setter
@Getter
@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int age;
    private String email;
}
