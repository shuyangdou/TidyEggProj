package com.tidy.tidyegg.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.fragment.MineOrderFrag;
import com.tidy.tidyegg.widget.MyViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/10.
 * 我的订单界面
 */
public class MyOrderAty extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.head_myorder)
    RelativeLayout head_myorder;
    @BindView(R.id.ll_mo_payfor)
    LinearLayout ll_mo_payfor;
    @BindView(R.id.ll_mo_sendgoods)
    LinearLayout ll_mo_sendgoods;
    @BindView(R.id.ll_mo_regoods)
    LinearLayout ll_mo_regoods;
    @BindView(R.id.ll_mo_evaluation)
    LinearLayout ll_mo_evaluation;
    @BindView(R.id.viewp_myorder)
    MyViewPager viewp_myorder;

    private int[] type = {1, 2, 3, 4};
    private MyOrderAdapter myOrderAdapter;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Bundle bundle = getBundle();
        if (bundle != null){
            mPosition = bundle.getInt("position");
        }
        myOrderAdapter = new MyOrderAdapter(this.getSupportFragmentManager(),type);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_myorder;
    }

    public void showView() {
        this.setTitle(head_myorder, "我的订单");
        this.setLeft(head_myorder);
        ll_mo_payfor.setOnClickListener(this);
        ll_mo_sendgoods.setOnClickListener(this);
        ll_mo_regoods.setOnClickListener(this);
        ll_mo_evaluation.setOnClickListener(this);
        viewp_myorder.setLocked(true);
        viewp_myorder.setOffscreenPageLimit(3);
        clearBack();
        if (mPosition == 0){
            ll_mo_payfor.setBackgroundColor(0xffffd600);
        }else if (mPosition == 1){
            ll_mo_sendgoods.setBackgroundColor(0xffffd600);
        }else if (mPosition == 2){
            ll_mo_regoods.setBackgroundColor(0xffffd600);
        }else if (mPosition == 3){
            ll_mo_evaluation.setBackgroundColor(0xffffd600);
        }
        viewp_myorder.setAdapter(myOrderAdapter);
        viewp_myorder.setCurrentItem(mPosition);//这个方法一定要在setAdapter 之后
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mo_payfor:
                clearBack();
                ll_mo_payfor.setBackgroundColor(0xffffd600);
                viewp_myorder.setCurrentItem(0);
                break;
            case R.id.ll_mo_sendgoods:
                clearBack();
                ll_mo_sendgoods.setBackgroundColor(0xffffd600);
                viewp_myorder.setCurrentItem(1);
                break;
            case R.id.ll_mo_regoods:
                clearBack();
                ll_mo_regoods.setBackgroundColor(0xffffd600);
                viewp_myorder.setCurrentItem(2);
                break;
            case R.id.ll_mo_evaluation:
                clearBack();
                ll_mo_evaluation.setBackgroundColor(0xffffd600);
                viewp_myorder.setCurrentItem(3);
                break;
        }
    }

    public void clearBack(){
        ll_mo_payfor.setBackgroundColor(0xffffffff);
        ll_mo_sendgoods.setBackgroundColor(0xffffffff);
        ll_mo_regoods.setBackgroundColor(0xffffffff);
        ll_mo_evaluation.setBackgroundColor(0xffffffff);
    }
    /**
     * 适配器
     */
    class MyOrderAdapter extends FragmentPagerAdapter {

        private int[] Type;
        public MyOrderAdapter(FragmentManager fm, int[] Type) {
            super(fm);
            this.Type = Type;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = new MineOrderFrag();
            Bundle bundle = new Bundle();
            bundle.putInt("type", Type[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
