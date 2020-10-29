package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author chenwei
 * @since 2020-10-16 15:13:27
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = -70839564227028973L;

    private Integer id;

    private String username;

    private String passwd;

    private String phonenumber;

    private String uuid;
    /**
     * 1代表超级管理员。0代表普通管理yuan
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}