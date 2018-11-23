package com.yui.study.crawler.enums;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 蓝月亮网站url
 *
 * @author XuZhuohao
 * @date 2018/11/23
 */
public enum BlueMoonUrlEnums {
    /**
     *
     */
    LoginIndexHtml("", RequestMethod.GET),
    ValiCodeImg("", RequestMethod.GET),
    LoginUrl("", RequestMethod.POST)
    ;
    private final static String MAIN_HOST = "";
    private String url;
    /**
     * RequestMethod.GET
     */
    private RequestMethod methodType;

    BlueMoonUrlEnums(String url, RequestMethod methodType) {
        this.url = MAIN_HOST + url;
        this.methodType = methodType;
    }
    public String getUrl(){
        return this.url;
    }
}
