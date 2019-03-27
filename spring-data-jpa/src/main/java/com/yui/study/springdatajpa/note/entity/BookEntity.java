package com.yui.study.springdatajpa.note.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 书
 *
 * @author XuZhuohao
 * @date 2019/2/25
 */
@Getter
@Setter
@Table(name = "book",indexes = {@Index(name="index_book_name", columnList = "name,createTime")})
@Entity
@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "bookId"))})
public class BookEntity extends BaseEntity {
    @Column(name="name")
    private String name;

    @Transient
    private String test;
}
