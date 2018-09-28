package com.lei.tang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanglei
 * @date 18/9/26
 */
@Controller
@RequestMapping("err")
public class ErrorController {

    @GetMapping("/error")
    public void error() {
        int i = 1 / 0;
    }
}
