package com.tidy.tidyegg.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 000009/10/9.
 * 今日上新商品entity
 */
public class TodayNewGoods implements Parcelable {
    private int image;
    private String goodsName;
    private double goodsPrice;
    private String goodsID;
    private String goodsDeposit;// 订金
    private int eggType;// 何种类型的鸡蛋，1为宝宝蛋，2为孕妇蛋，3为青少年蛋，4为老人蛋
    private int salesNum;// 销量

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public int getEggType() {
        return eggType;
    }

    public void setEggType(int eggType) {
        this.eggType = eggType;
    }

    public String getGoodsDeposit() {
        return goodsDeposit;
    }

    public void setGoodsDeposit(String goodsDeposit) {
        this.goodsDeposit = goodsDeposit;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public TodayNewGoods() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.goodsName);
        dest.writeDouble(this.goodsPrice);
        dest.writeString(this.goodsID);
        dest.writeString(this.goodsDeposit);
        dest.writeInt(this.eggType);
        dest.writeInt(this.salesNum);
    }

    protected TodayNewGoods(Parcel in) {
        this.image = in.readInt();
        this.goodsName = in.readString();
        this.goodsPrice = in.readDouble();
        this.goodsID = in.readString();
        this.goodsDeposit = in.readString();
        this.eggType = in.readInt();
        this.salesNum = in.readInt();
    }

    public static final Creator<TodayNewGoods> CREATOR = new Creator<TodayNewGoods>() {
        @Override
        public TodayNewGoods createFromParcel(Parcel source) {
            return new TodayNewGoods(source);
        }

        @Override
        public TodayNewGoods[] newArray(int size) {
            return new TodayNewGoods[size];
        }
    };
}
