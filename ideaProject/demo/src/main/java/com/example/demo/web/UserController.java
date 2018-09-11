package com.example.demo.web;

import com.example.demo.domain.Test;
import com.example.demo.domain.UUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/allUser")
    public String hello(HttpServletRequest request){
        List<UUser> allUser = this.userService.getAllUser();
        request.setAttribute("username","KING");
        request.setAttribute("allUser",allUser);
        return "index";
    }
}
