package com.tidy.tidyegg.event;

/**
 * Created by qiang on 16/10/17.
 * 是否向购物车添加商品事件
 */
public class ShoppingGoodsEvent {
    /**
     * 是否向购物车添加商品的flag，true为向购物车添加商品
     */
    public boolean isAdd;

    public ShoppingGoodsEvent(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
