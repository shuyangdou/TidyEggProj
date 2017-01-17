package com.tidy.tidyegg.bean;

/**
 * Created by qiang on 16/10/18.
 * 各种鸡蛋商品的评价详情entity
 */
public class ValuationDetail {
    private String user;
    private String time;
    private String content;

    public ValuationDetail(String user, String time, String content) {
        this.user = user;
        this.time = time;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
