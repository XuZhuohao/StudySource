package com.yui.teacher;

import com.yui.teacher.chapter1.Man;
import com.yui.teacher.chapter1.Pen;
import com.yui.teacher.chapter1.Person;
import com.yui.teacher.chapter1.Women;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ){
        // 对象1
        Pen redPen = new Pen();
        redPen.setColor("red");
        redPen.setLength(12);
        redPen.setPrice(100);
        /*// 对象 2
        Pen yellowPen = new Pen();
        yellowPen.setColor("yellow");
        yellowPen.setLength(12);
        yellowPen.setPrice(100);
        // 对象 人
        Person yellowPerson = new Person();
        yellowPerson.setHeavy(65);
        yellowPerson.setHeight(170);
        yellowPerson.setAge(21);
        yellowPerson.setName("许卓洁的小蛙仔");
        yellowPerson.setPen(yellowPen);
        System.out.println(yellowPerson.toString());
        // 对象 人2
        Person redPerson = new Person();
        redPerson.setHeavy(65);
        redPerson.setHeight(170);
        redPerson.setAge(21);
        redPerson.setName("许可证");
        redPerson.setPen(redPen);
        System.out.println(redPerson.toString());
        yellowPerson.saying("你是谁？");
        redPerson.saying("我是你爸");*/

        /*Man man = new Man();
        man.setHeavy(65);
        man.setHeight(170);
        man.setAge(21);
        man.setName("许卓洁的小蛙仔");
        man.setPen(redPen);
        man.saying("我是个男人！！");
        man.peeing();

        Women women = new Women();
        women.setHeavy(65);
        women.setHeight(170);
        women.setAge(21);
        women.setName("许卓洁的小蛙仔它老婆");
        women.setPen(redPen);
        women.saying("我是个女人！！");
        women.peeing();
        women.gaveBirthTo();*/
        Person p1 ;
        p1 = new Women();
        p1.peeing();
    }
}
