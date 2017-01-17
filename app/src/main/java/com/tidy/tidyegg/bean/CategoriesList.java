package com.tidy.tidyegg.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 16/10/12.
 * 商品分类某一具体分类列表的单个商品entity
 */
public class CategoriesList implements Parcelable {
    private int imageID;
    private String goodsID;
    private String goodsName;
    private double goodsPrice;
    private String goodsDeposit;// 订金
    private int eggType;// 何种类型的鸡蛋，1为宝宝蛋，2为孕妇蛋，3为青少年蛋，4为老人蛋
    private int salesNum;// 销量

    public CategoriesList(int imageID, String goodsID, String goodsName, double goodsPrice, String goodsDeposit, int eggType, int salesNum) {
        this.imageID = imageID;
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsDeposit = goodsDeposit;
        this.eggType = eggType;
        this.salesNum = salesNum;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsDeposit() {
        return goodsDeposit;
    }

    public void setGoodsDeposit(String goodsDeposit) {
        this.goodsDeposit = goodsDeposit;
    }

    public int getEggType() {
        return eggType;
    }

    public void setEggType(int eggType) {
        this.eggType = eggType;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imageID);
        dest.writeString(this.goodsID);
        dest.writeString(this.goodsName);
        dest.writeDouble(this.goodsPrice);
        dest.writeString(this.goodsDeposit);
        dest.writeInt(this.eggType);
        dest.writeInt(this.salesNum);
    }

    protected CategoriesList(Parcel in) {
        this.imageID = in.readInt();
        this.goodsID = in.readString();
        this.goodsName = in.readString();
        this.goodsPrice = in.readDouble();
        this.goodsDeposit = in.readString();
        this.eggType = in.readInt();
        this.salesNum = in.readInt();
    }

    public static final Creator<CategoriesList> CREATOR = new Creator<CategoriesList>() {
        @Override
        public CategoriesList createFromParcel(Parcel source) {
            return new CategoriesList(source);
        }

        @Override
        public CategoriesList[] newArray(int size) {
            return new CategoriesList[size];
        }
    };
}
