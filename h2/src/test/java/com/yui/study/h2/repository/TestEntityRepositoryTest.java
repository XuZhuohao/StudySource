package com.yui.study.h2.repository;

import com.yui.study.h2.H2ApplicationTests;
import com.yui.study.h2.entity.TestEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class TestEntityRepositoryTest extends H2ApplicationTests {
    @Autowired
    TestEntityRepository testEntityRepository;

    @Test
    public void save() {
        TestEntity testEntity = new TestEntity();
        testEntity.setAge(10);
        testEntity.setEmail("786725551@qq.com");
        testEntity.setName("test");
        testEntityRepository.save(testEntity);
    }

    @Test
    public void find() {
        final List<TestEntity> all = testEntityRepository.findAll();
        all.forEach(testEntity -> System.out.printf("id:%d, name:%s, age:%s, email:%s",
                testEntity.getId(), testEntity.getName(), testEntity.getAge(), testEntity.getEmail()));
    }
}