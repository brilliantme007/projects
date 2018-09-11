package com.whl.datafiles.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties()
@PropertySource("classpath:/wxpay.properties")
public class WxpayConfig {

    private String appId;
    private String mchId;
    private String key;
    private String cerUrl;
    private String useSandBox;


    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getMchId() {
        return mchId;
    }
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    public String getCerUrl() {
        return cerUrl;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setCerUrl(String cerUrl) {
        this.cerUrl = cerUrl;
    }
    public String getUseSandBox() {
        return useSandBox;
    }
    public void setUseSandBox(String useSandBox) {
        this.useSandBox = useSandBox;
    }

    public boolean isUsebox() {
        return "1".equals(useSandBox)?true:false;
    }
}
