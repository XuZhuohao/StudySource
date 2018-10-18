package com.yui.study.springdatajpa.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity{
    @Basic
    @Column(name = "username", nullable = false)
    private String name;

    @Basic
    @Column(name = "loginId", unique = true, nullable = false)
    private String loginId;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;


    @ManyToOne(targetEntity = ClassEntity.class, cascade={CascadeType.PERSIST})
    @JoinColumn(name = "class_id")
    private ClassEntity classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClassEntity getClasses() {
        return classes;
    }

    public void setClasses(ClassEntity classes) {
        this.classes = classes;
    }
}
