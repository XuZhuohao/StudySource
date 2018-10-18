package com.yui.study.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Setter
@Getter
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity{
    @Basic
    @Column(name = "username", nullable = false)
    private String name;

    @Basic
    @Column(name = "login_id", unique = true, nullable = false)
    private String loginId;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;


    @ManyToOne(targetEntity = ClassEntity.class, cascade={CascadeType.DETACH})
    @JoinColumn(name = "class_id")
    private ClassEntity classes;
}
