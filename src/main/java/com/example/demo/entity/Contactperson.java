package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Contactperson)实体类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
public class Contactperson implements Serializable {
    private static final long serialVersionUID = -51974656531294371L;

    private Integer id;

    private String name;

    private String gender;

    private String phonenumber;

    private String homephonenumber;

    private String wechat;

    private String email;

    private String identity;

    private String cid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getHomephonenumber() {
        return homephonenumber;
    }

    public void setHomephonenumber(String homephonenumber) {
        this.homephonenumber = homephonenumber;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}