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

    private String notes;

    private Integer role;

    public Integer getGuid(){
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
}