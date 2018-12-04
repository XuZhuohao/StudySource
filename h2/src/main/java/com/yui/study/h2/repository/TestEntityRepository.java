package com.yui.study.h2.repository;

import com.yui.study.h2.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author XuZhuohao
 * @date 2018/12/4
 */
public interface TestEntityRepository extends JpaRepository<TestEntity,Integer> {
}
