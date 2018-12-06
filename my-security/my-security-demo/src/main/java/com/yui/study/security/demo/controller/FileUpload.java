package com.yui.study.security.demo.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 *
 * @author XuZhuohao
 * @date 2018/12/6
 */
@RestController
@RequestMapping("/file")
public class FileUpload {
    @PostMapping
    public String upload(MultipartFile file, HttpServletRequest httpRequest) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String path = "src/main/resources/test";
        File localFile = new File(path, System.currentTimeMillis()+".txt");
        file.transferTo(localFile);
        return localFile.getCanonicalPath();
    }
}
