package com.metoo.web.controller.backup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlHandler {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
