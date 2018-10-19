package com.yui.study.springdatajpa.entity;

import com.yui.study.springdatajpa.repository.AnnotationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 笔记代码演示源码
 *
 * @author XuZhuohao
 * @date 2018/10/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteEntityTest {
    @Autowired
    private AnnotationRepository annotationRepository;

    /**
     * quick start
     */
    @Test
    public void testQuickStart(){
        AnnotationEntity annotationEntity = new AnnotationEntity();
        annotationEntity.setId(1L);
        annotationEntity.setName("test01");
        annotationRepository.save(annotationEntity);
    }
    @Test
    public void testBasic(){
        // @Basic 注解测试



    }
}
