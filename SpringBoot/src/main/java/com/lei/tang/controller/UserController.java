package com.lei.tang.controller;

import com.lei.tang.domain.ResourceBean;
import com.lei.tang.domain.ResponseBean;
import com.lei.tang.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanglei
 * @date 18/9/25
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    ResourceBean resourceBean;

    @GetMapping(value = "/getUser")
    public ResponseBean getUser() {
        User user = new User();
        user.setName("测试");
        user.setAge(100);
        return ResponseBean.ok(user);
    }

    @GetMapping(value = "/getResource", produces = "application/json;charset=utf-8")
    public ResponseBean getResource() {
        ResourceBean bean = new ResourceBean();
        BeanUtils.copyProperties(resourceBean, bean);
        return ResponseBean.ok(bean);
    }
}
