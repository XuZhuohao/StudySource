package com.yui.study.springdatajpa.repository;

import com.yui.study.springdatajpa.entity.AnnotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository
 *
 * @author XuZhuohao
 * @date 2018/10/19
 */
public interface AnnotationRepository extends JpaRepository<AnnotationEntity, Long> {
}
