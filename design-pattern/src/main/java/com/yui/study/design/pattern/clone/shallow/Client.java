package com.yui.study.design.pattern.clone.shallow;

/**
 * @author XuZhuohao
 * @date 2019/12/25
 */
public class Client {
    public static void main(String[] args) {
        final Phone phone = new Phone();
        phone.setName("小M手机");
        phone.setPrice("150");
        final Owner owner = new Owner();
        owner.setName("小明");
        phone.setOwner(owner);

        final Phone phoneClone = phone.clone();
        phoneClone.getOwner().setName("小江");
        System.out.println("phone name:" + phone.getName());
        System.out.println("phone Price:" + phone.getPrice());
        System.out.println("phone Owner.name:" + phone.getOwner().getName());
        System.out.println("===================");
        System.out.println("phoneClone name:" + phoneClone.getName());
        System.out.println("phoneClone Price:" + phoneClone.getPrice());
        System.out.println("phoneClone Owner.name:" + phoneClone.getOwner().getName());

    }
}
