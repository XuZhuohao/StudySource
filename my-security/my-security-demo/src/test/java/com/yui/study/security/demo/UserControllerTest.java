package com.yui.study.security.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * userController测试用例
 *
 * @author XuZhuohao
 * @date 2018-09-17 23:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("name", "test")
                //.param("t1", "test00000001")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGenUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGenUserInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateUserSuccess() throws Exception {
        String instr = "{\"birthDate\":1537347954443,\"name\":\"myName\",\"password\":\"password\"}";
        System.out.println(instr);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(instr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
        // TODO: 时间戳问题？
    }

    @Test
    public void whenCreateUserFail() throws Exception {
        // name blank
        // password null
        String instr = "{\"birthDate\":1537347954443,\"name\":\"\",\"password\":\"password\"}";
        System.out.println(instr);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(instr))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
