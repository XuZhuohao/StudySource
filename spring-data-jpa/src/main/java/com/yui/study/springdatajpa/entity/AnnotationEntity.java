package com.yui.study.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 注解学习实体类
 *
 * @author XuZhuohao
 * @date 2018/10/19
 */
@Setter
@Getter
@Entity(name = "annotation")
@Table()
public class AnnotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(fetch = FetchType.LAZY, optional = false)
    private String name;
}
