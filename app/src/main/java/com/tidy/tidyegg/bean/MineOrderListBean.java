package com.tidy.tidyegg.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dou on 2016/10/11.
 * 我的订单数据对象
 */
public class MineOrderListBean implements Serializable{

    private static final long serialVersionUID = -9108994009400791620L;
    private String orderId;
    private String topay;
    private List<Goods> goods;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public String getTopay() {
        return topay;
    }

    public void setTopay(String topay) {
        this.topay = topay;
    }

    public static class Goods implements Serializable{

        private static final long serialVersionUID = 898710855426284568L;
        private String name;
        private String price;
        private String num;
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
}
