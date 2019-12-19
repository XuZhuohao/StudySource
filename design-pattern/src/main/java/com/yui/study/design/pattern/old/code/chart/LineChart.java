package com.yui.study.design.pattern.old.code.chart;

/**
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class LineChart {
    public static final String TYPE = "line";

    public void display(Object[][] data) {
        System.out.println(TYPE + ":" + data);
    }
}
