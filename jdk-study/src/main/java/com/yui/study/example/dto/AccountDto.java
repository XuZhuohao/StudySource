package com.yui.study.example.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author XuZhuohao
 * @date 2020/06/01
 */
@Data
@Accessors(chain = true)
public class AccountDto {

    private String username;

    private String passwordMd5;

    private List<String> permissions;

    private Object obj;

}
