package com.yui.study.springdatajpa.entity;

import com.yui.study.springdatajpa.SpringDataJpaApplicationTests;
import com.yui.study.springdatajpa.note.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class StudentEntityTest extends SpringDataJpaApplicationTests {

    @Autowired
    StudentRepository studentRepository;
    @Test
    public void initTest(){
        System.out.println(studentRepository.findAll().size());
    }


}