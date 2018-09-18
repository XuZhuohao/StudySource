package com.yui.teacher.chapter1;

/**
 * 笔
 *
 * @author XuZhuohao
 * @date 2018-09-16 13:51
 */
public class Pen {
    /**
     * 长度
     */
    private int length;
    /**
     * 颜色
     */
    private String color;
    /**
     * 价格
     */
    private double price;

    @Override
    public String toString() {
        return "Pen{" +
                "length=" + length +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
