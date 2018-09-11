package com.whl.datafiles.web;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class HtmlController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }





}
