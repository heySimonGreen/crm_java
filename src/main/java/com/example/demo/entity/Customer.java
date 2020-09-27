package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Customer)实体类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 927527170531595756L;

    private Integer guid;

    private String username;


    public Integer getGuid() {
        return guid;
    }

    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}