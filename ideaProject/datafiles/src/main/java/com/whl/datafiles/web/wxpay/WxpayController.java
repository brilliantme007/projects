package com.whl.datafiles.web.wxpay;

import com.whl.datafiles.config.WxpayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/wxpay")
public class WxpayController {

    @Autowired
    private WxpayConfig wxpayConfig;

    @RequestMapping("/index.html")
    public String index(){
        return "wxpay/index";
    }
}
