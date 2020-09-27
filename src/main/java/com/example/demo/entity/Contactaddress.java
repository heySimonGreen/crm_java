package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Contactaddress)实体类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:53
 */
public class Contactaddress implements Serializable {
    private static final long serialVersionUID = -10768732827682160L;

    private Integer id;

    private Integer cid;

    private String title;

    private Integer stampnumber;

    private String country;

    private String province;

    private String city;

    private String district;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStampnumber() {
        return stampnumber;
    }

    public void setStampnumber(Integer stampnumber) {
        this.stampnumber = stampnumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

}