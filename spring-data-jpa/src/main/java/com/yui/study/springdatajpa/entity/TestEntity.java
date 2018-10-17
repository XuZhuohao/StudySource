package com.yui.study.springdatajpa.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * test实体类，测试用
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Table(name = "test")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    /**
     * @EnableJpaAuditing
     * @EntityListeners(AuditingEntityListener.class)
     * @CreatedDate
     * 才能使@CreatedDate生效
     */
    @Basic
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
