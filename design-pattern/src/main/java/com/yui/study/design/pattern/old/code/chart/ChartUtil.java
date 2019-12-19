package com.yui.study.design.pattern.old.code.chart;

/**
 * 图像类
 *
 * @author XuZhuohao
 * @date 2019/12/19
 */
public class ChartUtil {
    public static void main(String[] args) {
        final Object[][] objects = new Object[3][3];
        ChartUtil.display(objects, HistogramChart.TYPE);
    }


    public static void display(Object[][] data, String type) {
        if (type.equalsIgnoreCase(HistogramChart.TYPE)) {
            //初始化柱状图
            new HistogramChart().display(data);
        } else if (type.equalsIgnoreCase(PieChart.TYPE)) {
            //初始化饼状图
            new PieChart().display(data);
        } else if (type.equalsIgnoreCase(LineChart.TYPE)) {
            //初始化折线图
            new LineChart().display(data);
        }
    }
}
