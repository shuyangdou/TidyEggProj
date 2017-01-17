package com.tidy.tidyegg.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiang on 000011/10/11.
 * 商品秒杀Entity
 */
public class GoogsSpike implements Parcelable {
    private int image;
    private String goodsNum;// 限量数
    private String goodsName;
    private String deposit;// 订金
    private double price;// 售价
    private long startTime;// 开始日期的毫秒数
    private long endTime;// 结束日期的毫秒数
    private int status;// 当前秒杀的状态：已结束（0），正在进行（1），尚未开启（2）
    private String goodsID;
    private int eggType;// 何种类型的鸡蛋，1为宝宝蛋，2为孕妇蛋，3为青少年蛋，4为老人蛋
    private int salesNum;// 销量

    public GoogsSpike(int image, String goodsNum, String goodsName, String deposit, double price, long startTime, long endTime, int status, String goodsID, int eggType, int salesNum) {
        this.image = image;
        this.goodsNum = goodsNum;
        this.goodsName = goodsName;
        this.deposit = deposit;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.goodsID = goodsID;
        this.eggType = eggType;
        this.salesNum = salesNum;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.goodsNum);
        dest.writeString(this.goodsName);
        dest.writeString(this.deposit);
        dest.writeDouble(this.price);
        dest.writeLong(this.startTime);
        dest.writeLong(this.endTime);
        dest.writeInt(this.status);
        dest.writeString(this.goodsID);
        dest.writeInt(this.eggType);
        dest.writeInt(this.salesNum);
    }

    protected GoogsSpike(Parcel in) {
        this.image = in.readInt();
        this.goodsNum = in.readString();
        this.goodsName = in.readString();
        this.deposit = in.readString();
        this.price = in.readDouble();
        this.startTime = in.readLong();
        this.endTime = in.readLong();
        this.status = in.readInt();
        this.goodsID = in.readString();
        this.eggType = in.readInt();
        this.salesNum = in.readInt();
    }

    public static final Creator<GoogsSpike> CREATOR = new Creator<GoogsSpike>() {
        @Override
        public GoogsSpike createFromParcel(Parcel source) {
            return new GoogsSpike(source);
        }

        @Override
        public GoogsSpike[] newArray(int size) {
            return new GoogsSpike[size];
        }
    };
}
