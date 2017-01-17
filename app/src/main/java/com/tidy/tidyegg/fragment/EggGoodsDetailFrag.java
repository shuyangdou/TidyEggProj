package com.tidy.tidyegg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiang on 16/10/18.
 * 各类型鸡蛋的商品详情数据
 */
public class EggGoodsDetailFrag extends BaseFragment {
    @BindView(R.id.iv_ji)
    ImageView ivJi;
    @BindView(R.id.iv_description)
    ImageView ivDescription;

    private int eggType;// 鸡蛋所属人群的类型

    public EggGoodsDetailFrag() {
    }

    public EggGoodsDetailFrag(int flag) {
        this.eggType = flag;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_egg_goodsdetail;
    }

    @Override
    protected void initData() {
        if (eggType == 1) {
            ivJi.setBackgroundResource(R.mipmap.ji_bb);
        } else if (eggType == 2) {
            ivJi.setBackgroundResource(R.mipmap.ji_yf);
        } else if (eggType == 3) {
            ivJi.setBackgroundResource(R.mipmap.ji_sn);
        } else if (eggType == 4) {
            ivJi.setBackgroundResource(R.mipmap.ji_lr);
        }
    }

}
