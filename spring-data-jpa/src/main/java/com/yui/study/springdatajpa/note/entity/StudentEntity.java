package com.yui.study.springdatajpa.note.entity;

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
@Entity
@Table(name = "student",
        uniqueConstraints = {@UniqueConstraint(name = "unique_stu_idCardNo", columnNames = "idCardNo")},
        indexes = {@Index(name = "index_stu_loginId", columnList = "studNo", unique = true),
                @Index(name = "index_stu_age_height_weight", columnList = "age,height,weight")},
        catalog = "spring_data_jpa", schema = "removal")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String idCardNo;

    private int studNo;

    private int age;

    private double height;

    private double weight;
}
