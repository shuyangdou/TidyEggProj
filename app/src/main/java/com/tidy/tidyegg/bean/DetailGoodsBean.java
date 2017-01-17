package com.tidy.tidyegg.bean;

/**
 * Created by dou on 2016/10/12.
 * 订单详情中商品list
 */
public class DetailGoodsBean {
    private String name;
    private String price;
    private String num;
    private String picId;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
