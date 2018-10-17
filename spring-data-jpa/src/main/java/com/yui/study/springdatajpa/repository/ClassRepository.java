package com.yui.study.springdatajpa.repository;

import com.yui.study.springdatajpa.entity.ClassEntity;
import com.yui.study.springdatajpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
