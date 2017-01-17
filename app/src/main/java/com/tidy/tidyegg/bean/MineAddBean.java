package com.tidy.tidyegg.bean;


import java.io.Serializable;

/**
 * Created by dou on 2016/10/11.
 * 我的收货地址Bean
 */
public class MineAddBean implements Serializable {

    private static final long serialVersionUID = 3299554848610889752L;
    private String name;
    private String phone;
    private String add;
    private String mo;

    public String getMo() {
        return mo;
    }

    public void setMo(String mo) {
        this.mo = mo;
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

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

}
