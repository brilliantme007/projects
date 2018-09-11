package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Entity
@Table(name="uuser")
public class UUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String loginname;
    private String password;
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
