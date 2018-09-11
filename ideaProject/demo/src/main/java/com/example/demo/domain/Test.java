package com.example.demo.domain;

import javax.persistence.*;


/**
 * Created by 王洪亮 on 2017/8/19.
 */
@Entity
@Table(name="test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}

