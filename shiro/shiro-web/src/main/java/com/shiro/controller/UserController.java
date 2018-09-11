package com.shiro.controller;

import com.shiro.domain.UserBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(UserBean bean) {
        UsernamePasswordToken token = new UsernamePasswordToken(bean.getUsername(), bean.getPassword());

        Subject subject = SecurityUtils.getSubject();
        try {
            //设置是否记住cookie
            token.setRememberMe(true);
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "登录成功";
    }

    @RequestMapping(value = "/testRoels", method = RequestMethod.GET)
    public String testRoels() {
        return "testRoels";
    }

    @RequestMapping(value = "/testRoels1", method = RequestMethod.GET)
    public String testRoels1() {
        return "testRoels1";
    }

    @RequestMapping(value = "/testPerms", method = RequestMethod.GET)
    public String testPerms() {
        return "testPerms";
    }

    @RequestMapping(value = "/testPerms1", method = RequestMethod.GET)
    public String testPerms1() {
        return "testPerms1";
    }
}
