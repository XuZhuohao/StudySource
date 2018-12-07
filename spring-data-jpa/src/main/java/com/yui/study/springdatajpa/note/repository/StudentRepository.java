package com.yui.study.springdatajpa.note.repository;

import com.yui.study.springdatajpa.note.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository
 *
 * @author XuZhuohao
 * @date 2018/10/19
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
