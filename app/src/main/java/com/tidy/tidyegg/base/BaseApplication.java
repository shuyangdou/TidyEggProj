package com.tidy.tidyegg.base;

import android.app.Application;
import android.util.DisplayMetrics;

import com.tidy.tidyegg.bean.ShoppingCartGoods;
import com.tidy.tidyegg.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiang on 2016/10/8.
 * 重写Application
 */
public class BaseApplication extends Application {
    /**
     * 本地存储用户加入购物车的商品
     */
    public List<ShoppingCartGoods> shoppingCartGoodsList = new ArrayList<ShoppingCartGoods>();

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;// 屏幕宽（像素，如：480px）
        int h_screen = dm.heightPixels;// 屏幕高（像素，如：800px）
        SpUtils.putSharePre(getApplicationContext(), "mobileInfo", "width", w_screen);
        SpUtils.putSharePre(getApplicationContext(), "mobileInfo", "height", h_screen);
    }

    /**
     * 获取本地购物车商品数据
     *
     * @return
     */
    public List<ShoppingCartGoods> getGoodsList() {
        return shoppingCartGoodsList;
    }

    /**
     * 往本地购物车添加商品
     *
     * @param goods 商品信息
     */
    public void addGoods(ShoppingCartGoods goods) {
        shoppingCartGoodsList.add(goods);
    }

    /**
     * 清除本地存储的购物车商品数据
     */
    public void clearGoodsList() {
        this.shoppingCartGoodsList.clear();
    }
}

