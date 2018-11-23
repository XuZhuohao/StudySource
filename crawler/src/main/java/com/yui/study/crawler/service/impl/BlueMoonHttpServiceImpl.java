package com.yui.study.crawler.service.impl;

import com.yui.study.crawler.enums.BlueMoonUrlEnums;
import com.yui.study.crawler.util.FileUtil;
import com.yui.study.crawler.util.OcrUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 蓝月亮门户网站测试
 *
 * @author XuZhuohao
 * @date 2018/11/22
 */
@Service
public class BlueMoonHttpServiceImpl extends BaseHttpService {

    @Override
    public void login(String userName, String password) {
        // 连接登陆页面，保存访问cookie
        this.doGet(BlueMoonUrlEnums.LoginIndexHtml.getUrl(), null, null);
        displayResult(BlueMoonUrlEnums.LoginIndexHtml.getUrl(), this.getResult());
        // 获取验证码并读取
        this.doGet(BlueMoonUrlEnums.ValiCodeImg.getUrl(), null, null);
        String valiCodeSavePath = "temp/vailImg/" + System.currentTimeMillis() + ".jpeg";
        try {
            // 创建保存路径
            FileUtil.mkdirDirectory(valiCodeSavePath);
            // 保存图片
            OcrUtil.downloadJPG(this.getIs(), valiCodeSavePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取验证码信息
        String valiCode = OcrUtil.getImgContent(valiCodeSavePath);
//        this.doPost(BlueMoonUrlEnums.LoginUrl.getUrl(), );
        // TODO: 加密解密规则，容我再看看= =
    }
    private void displayResult(String url, String result){
        System.out.println("=========================" + url + "=========================begin");
        System.out.println(result);
        System.out.println("=========================" + url + "=========================end");
    }
    private void GetCiphertext(){

    }
}
