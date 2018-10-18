package com.yui.study.springdatajpa.repository;

import com.yui.study.springdatajpa.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
