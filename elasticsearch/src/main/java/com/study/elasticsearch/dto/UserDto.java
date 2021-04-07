package com.study.elasticsearch.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author XuZhuohao
 * @date 2021/4/7
 */
@Data
@Accessors(chain = true)
@Builder
public class UserDto {
    private String name;
    private int age;
    private String address;
}
