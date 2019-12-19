package com.yui.study.design.pattern.simple_factory_pattern;


/**
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class ChartFactory {
    public static Chart getChart(String type) {
        if (type.equalsIgnoreCase(HistogramChart.TYPE)) {
            //初始化柱状图
            return new HistogramChart();
        } else if (type.equalsIgnoreCase(PieChart.TYPE)) {
            //初始化饼状图
            return new PieChart();
        } else if (type.equalsIgnoreCase(LineChart.TYPE)) {
            //初始化折线图
            return new LineChart();
        } else{
            throw new RuntimeException("没有对应的类型");
        }
    }
}
