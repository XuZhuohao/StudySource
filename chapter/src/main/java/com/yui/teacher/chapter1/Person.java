package com.yui.teacher.chapter1;

/**
 * 人
 *
 * @author XuZhuohao
 * @date 2018-09-16 13:54
 */
public class Person {
    private double heavy;
    private double height;
    private String name;
    private int age;
    private Pen pen;

    @Override
    public String toString() {
        return "Person{" +
                "heavy=" + heavy +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", pen=" + pen +
                '}';
    }

    public double getHeavy() {
        return heavy;
    }

    public void setHeavy(double heavy) {
        this.heavy = heavy;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pen getPen() {
        return pen;
    }

    public void setPen(Pen pen) {
        this.pen = pen;
    }
    public void saying(String word){
        System.out.println(this.name + ":  " + word);
    }
    public void peeing(){
        System.out.println("I'am peeing");
    }
}
