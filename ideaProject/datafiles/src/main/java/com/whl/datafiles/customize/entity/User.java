package com.whl.datafiles.customize.entity;

import com.whl.datafiles.customize.annotation.WElement;
import com.whl.datafiles.customize.annotation.WTable;

@WTable("uuser")
public class User{
    @WElement("fid")
    private String id;
    @WElement("12")
    private String age;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}