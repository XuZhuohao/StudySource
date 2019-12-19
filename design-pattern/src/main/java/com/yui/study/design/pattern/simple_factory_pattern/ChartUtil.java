package com.yui.study.design.pattern.simple_factory_pattern;


/**
 * 图像类
 *
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class ChartUtil {
    public static void main(String[] args) {
        final Object[][] objects = new Object[3][3];
        /// 原代码
//        ChartUtil.display(objects, HistogramChart.TYPE);
        ChartFactory.getChart(HistogramChart.TYPE).display(objects);
    }

}
