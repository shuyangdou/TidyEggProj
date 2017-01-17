package com.tidy.tidyegg.bean;

import java.util.List;

/**
 * Created by dou on 2016/10/14.
 * 物流信息
 */
public class LogisticsBean {
    private String OrderId;
    private List<Goods> goodsList;
    private List<LogisticsMessage> logisticsList;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<LogisticsMessage> getLogisticsList() {
        return logisticsList;
    }

    public void setLogisticsList(List<LogisticsMessage> logisticsList) {
        this.logisticsList = logisticsList;
    }

    public static class Goods{
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

    public static class LogisticsMessage{
        private String location;
        private String time;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
