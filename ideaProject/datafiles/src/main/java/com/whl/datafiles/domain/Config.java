package com.whl.datafiles.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/11/27.
 */
@Component
@ConfigurationProperties(prefix = "com.config")
public class Config {

    private String savefileurl;
    private int maxsavesize;

    public int getMaxsavesize() {
        return maxsavesize;
    }

    public void setMaxsavesize(int maxsavesize) {
        this.maxsavesize = maxsavesize;
    }

    public String getSavefileurl() {
        return savefileurl;
    }

    public void setSavefileurl(String savefileurl) {
        this.savefileurl = savefileurl;
    }

    @Override
    public String toString() {
        return "Config{" +
                "savefileurl='" + savefileurl + '\'' +
                '}';
    }
}
