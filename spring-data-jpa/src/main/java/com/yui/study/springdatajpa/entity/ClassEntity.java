package com.yui.study.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 班级Entity
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Setter
@Getter
@Entity
@Table(name = "class")
public class ClassEntity extends BaseEntity {
    @Basic
    @Column(name = "className", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private List<UserEntity> users;

}
