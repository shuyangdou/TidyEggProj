package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/12.
 * 设置中心
 */
public class MineSettingAty extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.head_minesetting)
    RelativeLayout head_minesetting;
    @BindView(R.id.rl_mineset_modifypass)
    RelativeLayout rl_modifypass;
    @BindView(R.id.rl_mineset_aboutus)
    RelativeLayout rl_aboutus;
    @BindView(R.id.rl_mineset_yijian)
    RelativeLayout rl_yijian;
    @BindView(R.id.rl_mineset_help)
    RelativeLayout rl_help;
    @BindView(R.id.rl_mineset_kefu)
    RelativeLayout rl_kefu;
    @BindView(R.id.tv_zhuxiao)
    TextView tv_zhuxiao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_minesetting;
    }

    public void showView(){
        setLeft(head_minesetting);
        setTitle(head_minesetting,"设置");

        rl_modifypass.setOnClickListener(this);
        rl_aboutus.setOnClickListener(this);
        rl_yijian.setOnClickListener(this);
        rl_help.setOnClickListener(this);
        rl_kefu.setOnClickListener(this);
        tv_zhuxiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /** 修改密码 */
            case R.id.rl_mineset_modifypass:
                startActivity(ModifyPasswordAty.class);
                break;
            /** 关于软件 */
            case R.id.rl_mineset_aboutus:
                break;
            /** 意见反馈 */
            case R.id.rl_mineset_yijian:
                startActivity(FeedbackAty.class);
                break;
            /** 帮助中心 */
            case R.id.rl_mineset_help:
                break;
            /** 一键客服 */
            case R.id.rl_mineset_kefu:
                break;
            /** 注销登录 */
            case R.id.tv_zhuxiao:
                break;
        }
    }
}
