package com.yui.study.security.demo.dao;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author XuZhuohao
 * @date 2018/9/19
 */
public class User {
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{}

    /**
     * @NotBlank
     */
    @NotBlank(message = "name must not be blank")
    private String name;

    private Long id;

    @NotNull(message = "password must not be null")
    @ApiModelProperty("用户id")
    private String password;

    private Date birthDate;

    @JsonView(UserSimpleView.class)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonView(UserSimpleView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
