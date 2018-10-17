package com.yui.study.springdatajpa.entity;

import com.yui.study.springdatajpa.repository.ClassRepository;
import com.yui.study.springdatajpa.repository.EntityRepository;
import com.yui.study.springdatajpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * test
 *
 * @author XuZhuohao
 * @date 2018/10/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class TestEntityTest {
    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;

    @Test
    public void test1() {
        // save
        TestEntity testEntity = new TestEntity();
        testEntity.setName("test3");
        entityRepository.save(testEntity);
        testEntity.setCreateTime(new Date());
        entityRepository.save(testEntity);
        // 通过 CreateTime 查找不到数据的问题
//        entityRepository.flush();
        // find by Example
        TestEntity findTestEntity = new TestEntity();
        //findTestEntity.setName("test3");
        findTestEntity.setCreateTime(testEntity.getCreateTime());
        Example<TestEntity> example = Example.of(findTestEntity);
        System.out.println("findTestEntity.getCreateTime():" + findTestEntity.getCreateTime());
        System.out.println("testEntity.getCreateTime():" + testEntity.getCreateTime());
        List<TestEntity> testEntityList = entityRepository.findAll(example);
        testEntityList.forEach(testEntity1 -> System.out.println(testEntity.getName() + ":" + testEntity.getCreateTime()));
        //System.out.println(testEntity.getId() + ":" + testEntity.getName() + ":" + testEntity.getCreateTime());
        System.out.println(testEntityList.size());
        System.out.println("findTestEntity.getCreateTime():" + findTestEntity.getCreateTime());
        System.out.println("testEntity.getCreateTime():" + testEntity.getCreateTime());
    }

    @Test
    public void testTime() throws ParseException {
        Optional<TestEntity> testEntity01 = entityRepository.findById(1);
        TestEntity findTestEntity = new TestEntity();
        findTestEntity.setCreateTime(testEntity01.get().getCreateTime());
        Example<TestEntity> example = Example.of(findTestEntity);
        List<TestEntity> testEntityList = entityRepository.findAll(example);
        System.out.println(testEntityList.size());
        testEntityList.forEach(testEntity -> System.out.println(testEntity.getName() + ":" + testEntity.getCreateTime()));
    }

    @Test
    public void testUpdateAfterSelect(){
        Optional<TestEntity> testEntity = entityRepository.findById(1);
        if (testEntity.isPresent()){
            System.out.println(testEntity.get().getName());
            testEntity.get().setCreateTime(new Date());
            entityRepository.save(testEntity.get());
        }
    }

    @Test
    public void testUpdate(){
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2);
        testEntity.setName("testzz");
        entityRepository.save(testEntity);
    }

    @Test
    public void testManyToOn(){
        ClassEntity classEntity = new ClassEntity();
        //classEntity.setId(1L);
        classEntity.setName("班级1");

        UserEntity userEntity = new UserEntity();
        userEntity.setClasses(classEntity);
        userEntity.setLoginId("test1");
        userEntity.setName("test");
        userEntity.setPassword("password");

        userRepository.save(userEntity);
    }
}
