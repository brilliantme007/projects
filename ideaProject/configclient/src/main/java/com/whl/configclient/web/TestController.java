package com.whl.configclient.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wang HLiang
 * @Description: Create a new file
 * @Date: Created in 14:53 2018-08-15
 * @Modofied:
 */
@RestController
@RefreshScope
public class TestController {
    @Value("${name}")
    private String name;

    @Autowired
    private Environment environment;

    @RequestMapping("/name")
    public String getName(){
        return this.name;
    }
    @RequestMapping("/name1")
    public String getName1(){
        return this.environment.getProperty("name");
    }
}
