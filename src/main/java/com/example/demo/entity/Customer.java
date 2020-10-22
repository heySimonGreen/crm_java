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

    private String guid;

    private String username;

    private String notes;

    private Integer role;

    private Integer adminid;

    private Integer isdelet;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public Integer getIsdelet() {
        return isdelet;
    }

    public void setIsdelet(Integer isdelet) {
        this.isdelet = isdelet;
    }
}