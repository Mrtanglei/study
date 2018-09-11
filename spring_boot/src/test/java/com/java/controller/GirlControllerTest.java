package com.java.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@Transactional//需要执行测试后数据回滚
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc//API测试需要用到
public class GirlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteGirl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/girl/delte").param("id", "3")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void saveGirl() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.put("name", Collections.singletonList("123456"));
        multiValueMap.put("age", Collections.singletonList("30"));
        mockMvc.perform(MockMvcRequestBuilders.post("/girl/girls").params(multiValueMap)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}