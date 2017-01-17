package com.tidy.tidyegg.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 16/10/13.
 * 购物车商品entity
 */
public class ShoppingCartGoods implements Parcelable {
    private String goodsID;
    private int imageID;
    private String goodsName;
    private double goodsPrice;
    private int goodsNum;
    private boolean isSpike;// 是否是秒杀商品

    public ShoppingCartGoods(String goodsID, int imageID, String goodsName, double goodsPrice, int goodsNum, boolean isSpike) {
        this.goodsID = goodsID;
        this.imageID = imageID;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsNum = goodsNum;
        this.isSpike = isSpike;
    }

    public boolean isSpike() {
        return isSpike;
    }

    public void setSpike(boolean spike) {
        isSpike = spike;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (this.getClass() == obj.getClass()) {
                ShoppingCartGoods goods = (ShoppingCartGoods) obj;
                if (this.getGoodsID().equals(goods.getGoodsID())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.goodsID);
        dest.writeInt(this.imageID);
        dest.writeString(this.goodsName);
        dest.writeDouble(this.goodsPrice);
        dest.writeInt(this.goodsNum);
        dest.writeByte(this.isSpike ? (byte) 1 : (byte) 0);
    }

    protected ShoppingCartGoods(Parcel in) {
        this.goodsID = in.readString();
        this.imageID = in.readInt();
        this.goodsName = in.readString();
        this.goodsPrice = in.readDouble();
        this.goodsNum = in.readInt();
        this.isSpike = in.readByte() != 0;
    }

    public static final Creator<ShoppingCartGoods> CREATOR = new Creator<ShoppingCartGoods>() {
        @Override
        public ShoppingCartGoods createFromParcel(Parcel source) {
            return new ShoppingCartGoods(source);
        }

        @Override
        public ShoppingCartGoods[] newArray(int size) {
            return new ShoppingCartGoods[size];
        }
    };
}
