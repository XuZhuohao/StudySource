package com.yui.study.springdatajpa.entity;

import com.alibaba.fastjson.JSON;
import com.yui.study.springdatajpa.repository.ClassRepository;
import com.yui.study.springdatajpa.repository.EntityRepository;
import com.yui.study.springdatajpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
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
    public void testManyToOne(){
        ClassEntity classEntity = new ClassEntity();
        //classEntity.setId(1L);
        classEntity.setName("班级1");

        UserEntity userEntity = new UserEntity();
        userEntity.setClasses(classEntity);
        userEntity.setLoginId("test1");
        userEntity.setName("test1");
        userEntity.setPassword("password1");

        UserEntity userEntity02 = new UserEntity();
        userEntity02.setClasses(classEntity);
        userEntity02.setLoginId("test2");
        userEntity02.setName("test2");
        userEntity02.setPassword("password2");
        List<UserEntity> userEntities = new ArrayList<>(16);
        userEntities.add(userEntity);
        userEntities.add(userEntity02);
        classRepository.save(classEntity);
        userRepository.saveAll(userEntities);

        // OneToMany search
        System.out.println("OneToMany search");
        List<ClassEntity> classEntityList = classRepository.findAll();
        classEntityList.forEach(classEntity1 ->
                classEntity1.getUsers().forEach(user ->
                        System.out.println(user.getName())));
    }

    @Test
    public void testSaveManyToOneExistsOne(){

        ClassEntity classEntity = new ClassEntity();
        classEntity.setId(1L);
        System.out.println(JSON.toJSONString(classEntity));

        UserEntity userEntity = new UserEntity();
        userEntity.setClasses(classEntity);
        userEntity.setLoginId("test3");
        userEntity.setName("test3");
        userEntity.setPassword("password3");
        userRepository.save(userEntity);
    }
    @Test
    public void testSimpleRowBounds(){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(1,1,sort);
        Page<UserEntity> page = userRepository.findAll(pageable);
        System.out.println("总共：" + page.getTotalPages() + "页" + page.isLast());
        while (!page.isLast()){
            System.out.println("分页查询：第" + page.getPageable().getPageNumber() + "页");
            List<UserEntity> userEntities = page.getContent();
            userEntities.forEach(userEntity -> System.out.println(JSON.toJSONString(userEntity)));
            page.getPageable().next();
        }
    }
}
