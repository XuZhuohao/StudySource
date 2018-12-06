package com.yui.study.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yui.study.security.demo.dao.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户信息控制器
 *
 * @author XuZhuohao
 * @date 2018-09-17 23:35
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUsers(@RequestParam String name, @RequestParam(defaultValue = "testDefault", name = "t1") String test){
        System.out.println(name);
        System.out.println(test);
        List<User> rtn = new ArrayList<>();
        rtn.add(new User());
        rtn.add(new User());
        rtn.add(new User());
        return rtn;
    }
    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User createUser(@Valid @RequestBody User user/*, BindingResult errs*/){
/*        if (errs.hasErrors()){
            errs.getAllErrors().forEach(err -> System.out.println(err.getDefaultMessage()));
        }*/
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthDate());
        user.setId(1L);
        return user;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("用户信息查询服务")
    public User getUserInfor(@PathVariable @ApiParam(value = "用户id") long id){
        User user = new User();
        user.setId(id);
        user.setName("tom");
        user.setPassword("mm");
        return user;
    }
}
