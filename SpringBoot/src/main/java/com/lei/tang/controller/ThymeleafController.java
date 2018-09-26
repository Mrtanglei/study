package com.lei.tang.controller;

import com.lei.tang.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tanglei
 * @date 18/9/26
 */
@Controller
@RequestMapping("th")
@Slf4j
public class ThymeleafController {

    @GetMapping(value = "/index")
    public String index() {
        return "thymeleaf/index";
    }

    @GetMapping(value = "/content")
    public String content(ModelMap modelMap){
        modelMap.addAttribute("name", "Thymeleaf");
        return "thymeleaf/content";
    }

    @GetMapping(value = "/test")
    public String test(ModelMap map){
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setAge(18);
        user.setName("Thymeleaf");
        user.setDate(new Date());
        user.setDesc("<label style='color: green'>this is Thymeleaf</label>");
        map.addAttribute("user", user);
        list.add(user);
        User user1 = new User();
        user1.setAge(10);
        user1.setName("Thymeleaf1");
        user1.setDate(new Date());
        user1.setDesc("<label style='color: green'>this is Thymeleaf</label>");
        list.add(user1);
        User user2 = new User();
        user2.setAge(30);
        user2.setName("Thymeleaf2");
        user2.setDate(new Date());
        user2.setDesc("<label style='color: green'>this is Thymeleaf</label>");
        list.add(user2);
        User user3 = new User();
        user3.setAge(40);
        user3.setName("Thymeleaf3");
        user3.setDate(new Date());
        user.setDesc("<label style='color: green'>this is Thymeleaf</label>");
        list.add(user3);
        map.addAttribute("list", list);
        return "thymeleaf/test";
    }

    @PostMapping(value = "/postForm")
    public String postForm(User user){
        log.info(user.getName());
        return "redirect:/th/test";
    }
}
