package com.java.controller;

import com.java.entity.GirlPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWordController {

    @Autowired
    private GirlPerson girlPerson;

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public @ResponseBody
    String sayHello() {
        return girlPerson.getName() + girlPerson.getAge();
    }
}
