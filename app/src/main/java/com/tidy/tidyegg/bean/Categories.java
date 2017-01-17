package com.tidy.tidyegg.bean;

/**
 * Created by qiang on 16/10/12.
 * 商品分类entity
 */
public class Categories {
    private String goodsName;
    private int imageID;

    public Categories(String goodsName, int imageID) {
        this.goodsName = goodsName;
        this.imageID = imageID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
