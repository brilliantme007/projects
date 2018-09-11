package com.whl.datafiles.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/11/27.
 */
@Entity
@Table(name="fileinfo")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fid;
    private String fname;
    private String furl;
    private String fdis;
    private Timestamp fcreatetime;
    private byte[] fb;
    private String endstiff;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEndstiff() {
        return endstiff;
    }

    public void setEndstiff(String endstiff) {
        this.endstiff = endstiff;
    }

    public byte[] getFb() {
        return fb;
    }

    public void setFb(byte[] fb) {
        this.fb = fb;
    }
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getFdis() {
        return fdis;
    }

    public void setFdis(String fdis) {
        this.fdis = fdis;
    }

    public Timestamp getFcreatetime() {
        return fcreatetime;
    }

    public void setFcreatetime(Timestamp fcreatetime) {
        this.fcreatetime = fcreatetime;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", furl='" + furl + '\'' +
                ", fdis='" + fdis + '\'' +
                ", fcreatetime=" + fcreatetime +
                '}';
    }
}
