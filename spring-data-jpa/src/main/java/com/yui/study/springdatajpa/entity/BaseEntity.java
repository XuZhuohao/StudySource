package com.yui.study.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础实体
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updateTime;

/* 需要实现 AuditorAware<Long>
    @CreatedBy
    private Long createId;

    @LastModifiedBy
    private Long cupdateId;
    */
}
