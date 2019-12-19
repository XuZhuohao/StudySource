package com.yui.study.design.pattern.simple_factory_pattern;

/**
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class HistogramChart implements Chart{
    public static final String TYPE = "histogram";
    @Override
    public void display(Object[][] data){
        System.out.println(TYPE + ":" + data);
    }
}
