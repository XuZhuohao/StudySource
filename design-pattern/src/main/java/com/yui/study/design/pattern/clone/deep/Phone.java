package com.yui.study.design.pattern.clone.deep;

import java.io.*;

/**
 * @author XuZhuohao
 * @date 2019/12/25
 */
public class Phone implements Serializable {
    private String name;
    private String price;
    private Owner owner;

    public Phone deepClone() {
        //将对象写入流中
        ByteArrayOutputStream bao = null ;
        ByteArrayInputStream bis = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            //将对象写入流中
            bao = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            //将对象从流中取出
            bis = new ByteArrayInputStream(bao.toByteArray());
            ois = new ObjectInputStream(bis);

            return (Phone) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (bao != null) {
                    bao.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
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
