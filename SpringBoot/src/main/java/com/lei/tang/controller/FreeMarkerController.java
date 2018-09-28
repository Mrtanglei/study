package com.lei.tang.controller;

import com.lei.tang.domain.ResourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanglei
 * @date 18/9/25
 */
@Controller
@RequestMapping("ftl")
public class FreeMarkerController {

    @Autowired
    ResourceBean resourceBean;

    @GetMapping(value = "/index")
    public String index() {
        return "freemarker/index";
    }

    @GetMapping(value = "/content")
    public String content(ModelMap modelMap) {
        modelMap.addAttribute("resourceBean", resourceBean);
        return "freemarker/content";
    }
}
