package com.yui.study.crawler.service.impl;

import com.yui.study.crawler.service.HttpService;
import lombok.Getter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * httpservice 基础抽象类
 *
 * @author XuZhuohao
 * @date 2018/11/22
 */
public abstract class BaseHttpService implements HttpService {
    @Getter
    private String result;
    @Getter
    private List<Header> responseHeaders = new ArrayList<>(16);
    @Getter
    private CookieStore cookieStore = new BasicCookieStore();
    @Getter
    private InputStream is;

    public void doGet(String url, Map<String, Object> paramMap, Map<String, String> headers){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = this.getHttpClient();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头
            this.setHeaderLikeChrome(httpGet);
            // 设置自定义请求头（覆盖通用请求头）
            headers.forEach(httpGet::setHeader);
            // 设置配置请求参数
            // 连接主机服务超时时间 // 请求超时时间 // 数据读取超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            this.setInfor(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void doPost(String url, Map<String, Object> paramMap, Map<String, String> headers) {
        CloseableHttpClient httpClient;
        CloseableHttpResponse httpResponse = null;
        // 通过cookie 创建httpClient实例
        httpClient = this.getHttpClient();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        // 设置连接主机服务超时时间 )// 设置连接请求超时时间 // 设置读取数据连接超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000)
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        this.setHeaderLikeChrome(httpPost);
        // 设置自定义请求头（覆盖通用请求头）
        headers.forEach(httpPost::setHeader);
        if (null != paramMap && paramMap.size() > 0) {
            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(buildPostParam(paramMap), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            this.setInfor(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<NameValuePair> buildPostParam(Map<String, Object> paramMap) {
        List<NameValuePair> nvps = new ArrayList<>();
        // 循环遍历，获取迭代器
        paramMap.forEach((key, value)-> nvps.add(new BasicNameValuePair(key, String.valueOf(value))) );
        return nvps;
    }

    /**
     * 设置通用谷歌浏览器请求头(常用 + 谷歌)
     * @param httpRequestBase request实例
     */
    private void setHeaderLikeChrome(HttpRequestBase httpRequestBase) {
        // 常用头设置
        this.setHeaderUsually(httpRequestBase);
        // User-Agent: 浏览器的身份标识字符串
        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
    }

    /**
     * 通用请求头
     * @param httpRequestBase request实例
     */
    private void setHeaderUsually(HttpRequestBase httpRequestBase) {
        // Accept: 可接受的响应内容类型（Content-Types）
        httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        // Accept-Encoding: 可接受的响应内容的编码方式
        httpRequestBase.setHeader("Accept-Encoding", "gzip, deflate, br");
        // Accept-Language: 可接受的响应内容语言列表
        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        // Cache-Control: 用来指定当前的请求/回复中的，是否使用缓存机制
        // max-age>0 时 直接从游览器缓存中 提取,
        // max-age<=0 时 向server 发送http 请求确认 ,该资源是否有修改 有的话 返回200 ,无的话 返回304
        // no-cache — 强制每次请求直接发送给源服务器，而不经过本地缓存版本的校验
        httpRequestBase.setHeader("Cache-Control", "max-age=0");
        // Connection: 客户端（浏览器）想要优先使用的连接类型 Connection: keep-alive Connection: Upgrade
        httpRequestBase.setHeader("Connection", "keep-alive");
        // Host: 表示服务器的域名以及服务器所监听的端口号。如果所请求的端口是对应的服务的标准端口（80），则端口号可以省略。
        httpRequestBase.setHeader("Host", httpRequestBase.getURI().getHost());
        // Upgrade-Insecure-Requests: http的资源请求，可以升级为https标志
        httpRequestBase.setHeader("Upgrade-Insecure-Requests", "1");
        // User-Agent: 浏览器的身份标识字符串
        // httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36")
        // Content-Length: 以8进制表示的请求体的长度
        // httpRequestBase.setHeader("Content-Length", "231")
        // Content-Type: application/x-www-form-urlencoded 需要根据请求定义
        // Origin: 发起一个针对跨域资源共享的请求（该请求要求服务器在响应中加入一个Access-Control-Allow-Origin的消息头，表示访问控制所允许的来源）
        // Referer: 表示浏览器所访问的前一个页面，可以认为是之前访问页面的链接将浏览器带到了当前页面。(单词意思其实就是Referrer了，历史原因)
    }
    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
    }

    /**
     *
     * @param httpResponse
     * @throws IOException
     */
    private void setInfor(CloseableHttpResponse httpResponse) throws IOException {
        // 清空全局头信息，把此次获取的头信息保存到全局
        this.responseHeaders.clear();
        this.responseHeaders.addAll(Arrays.asList(httpResponse.getAllHeaders()));
        // 从响应对象中获取响应内容
        HttpEntity entity = httpResponse.getEntity();
        this.result = EntityUtils.toString(entity);
        try {
            if (this.is != null) {
                this.is.close();
                is = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 输出流
        this.is = entity.getContent();
    }
}
