package com.yui.study.design.pattern.clone.shallow;

/**
 * @author XuZhuohao
 * @date 2019/12/25
 */
public class Phone implements Cloneable{
    private String name;
    private String price;
    private Owner owner;

    @Override
    public Phone clone(){
        try {
            return (Phone)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public Phone setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Phone setPrice(String price) {
        this.price = price;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public Phone setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }
}
