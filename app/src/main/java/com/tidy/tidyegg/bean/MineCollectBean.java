package com.tidy.tidyegg.bean;

/**
 * Created by dou on 2016/10/12.
 * 我的收藏bean对象
 */
public class MineCollectBean {
    private String name;
    private String deposit;
    private String pay;
    private String picid;

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
