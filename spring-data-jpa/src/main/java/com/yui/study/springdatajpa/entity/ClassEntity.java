package com.yui.study.springdatajpa.entity;

import org.hibernate.annotations.ListIndexBase;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 班级Entity
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Entity
@Table(name = "class")
public class ClassEntity extends BaseEntity {
    @Basic
    @Column(name = "className", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Set<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
