package com.yui.study.springdatajpa.repository;

import com.yui.study.springdatajpa.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
