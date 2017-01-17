package com.tidy.tidyegg.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.activity.MineAddAty;
import com.tidy.tidyegg.activity.MineCollectionAty;
import com.tidy.tidyegg.activity.MineSettingAty;
import com.tidy.tidyegg.activity.MyOrderAty;
import com.tidy.tidyegg.activity.PerCenterAty;
import com.tidy.tidyegg.base.BaseFragment;
import com.tidy.tidyegg.utils.DensityUtils;
import com.tidy.tidyegg.utils.SpUtils;
import com.tidy.tidyegg.widget.CircleImageView;

import butterknife.BindView;

/**
 * Created by dou on 2016/10/9.
 * 导航我的页面
 */
public class MineFrag extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.head_minefrag)
    RelativeLayout head_minefrag;//标题
    @BindView(R.id.appTitle_mine_setting)
    TextView appTitle_setting;
    @BindView(R.id.rl_minecenter)
    RelativeLayout rl_minecenter;//个人中心
    @BindView(R.id.image_headpic)
    CircleImageView image_headpic;//头像
    @BindView(R.id.tv_minename)
    TextView tv_minename;//昵称
    @BindView(R.id.tv_dengji)
    TextView tv_dengji;//等级
    @BindView(R.id.tv_zhekou)
    TextView tv_zhekou;//折扣

    @BindView(R.id.tv_minepayment)
    TextView tv_minepayment;//待付款
    @BindView(R.id.tv_minesendgoods)
    TextView tv_minesendgoods;//待发货
    @BindView(R.id.tv_minereceivegoods)
    TextView tv_minereceivegoods;//待收货
    @BindView(R.id.tv_mineevaluation)
    TextView tv_mineevaluation;//待评价
    @BindView(R.id.rl_mineadd)
    RelativeLayout rl_mineadd;//我的收货地址
    @BindView(R.id.image_myadd)
    ImageView image_myadd;//收货地址图片
    @BindView(R.id.rl_minecollection)
    RelativeLayout rl_minecollection;//我的收藏
    @BindView(R.id.image_mycollection)
    ImageView image_mycollection;//我的收藏图片

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        setTitle(head_minefrag,"我的");
        appTitle_setting.setVisibility(View.VISIBLE);
        appTitle_setting.setOnClickListener(this);
        rl_minecenter.setOnClickListener(this);
        tv_minepayment.setOnClickListener(this);
        tv_minesendgoods.setOnClickListener(this);
        tv_minereceivegoods.setOnClickListener(this);
        tv_mineevaluation.setOnClickListener(this);
        rl_mineadd.setOnClickListener(this);
        rl_minecollection.setOnClickListener(this);

        int width = SpUtils.getSharePreInt(mContext, "mobileInfo", "width");
        int imageWidth = (width - DensityUtils.dip2px(mContext, 20)) / 3;
        ViewGroup.LayoutParams params = image_headpic.getLayoutParams();
        params.width = imageWidth;
        params.height = params.width;
        image_headpic.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /** 个人中心 */
            case R.id.rl_minecenter:
                startActivity(PerCenterAty.class);
                break;
            /** 待付款 */
            case R.id.tv_minepayment:
                Bundle minepaymentbundle = new Bundle();
                minepaymentbundle.putInt("position",0);
                startActivity(MyOrderAty.class,minepaymentbundle);
                break;
            /** 待发货 */
            case R.id.tv_minesendgoods:
                Bundle minesendgoodsbundle = new Bundle();
                minesendgoodsbundle.putInt("position",1);
                startActivity(MyOrderAty.class,minesendgoodsbundle);
                break;
            /** 待收货 */
            case R.id.tv_minereceivegoods:
                Bundle minereceivegoodsbundle = new Bundle();
                minereceivegoodsbundle.putInt("position",2);
                startActivity(MyOrderAty.class,minereceivegoodsbundle);
                break;
            /** 待评价 */
            case R.id.tv_mineevaluation:
                Bundle mineevaluationbundle = new Bundle();
                mineevaluationbundle.putInt("position",3);
                startActivity(MyOrderAty.class,mineevaluationbundle);
                break;
            /** 我的收货地址 */
            case R.id.rl_mineadd:
                startActivity(MineAddAty.class);
                break;
            /** 我的收藏 */
            case R.id.rl_minecollection:
                startActivity(MineCollectionAty.class);
                break;
            /** 设置中心 */
            case R.id.appTitle_mine_setting:
                startActivity(MineSettingAty.class);
                break;
        }
    }
}
