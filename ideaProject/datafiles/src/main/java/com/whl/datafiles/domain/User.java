package com.whl.datafiles.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10.
 */
public class User implements Serializable {

    private static final long serialVersionUId = 1L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
