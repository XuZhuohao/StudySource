package com.yui.study.design.pattern.old.code.chart;

/**
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class HistogramChart {
    public static final String TYPE = "histogram";

    public void display(Object[][]  data){
        System.out.println(TYPE + ":" + data);
    }
}
