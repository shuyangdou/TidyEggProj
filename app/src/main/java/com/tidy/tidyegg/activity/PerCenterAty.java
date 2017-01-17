package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou 2016/10/10.
 * 个人中心页面
 */
public class PerCenterAty extends BaseActivity implements View.OnClickListener {
    /** 标题 */
    @BindView(R.id.head_percenter)
    RelativeLayout head_percenter;
    /** 正常情况下的信息 */
    @BindView(R.id.ll_percenter_normal)
    LinearLayout ll_percenter_normal;
    /** 正常情况下的昵称 */
    @BindView(R.id.tv_normal_name)
    TextView tv_normal_name;
    /** 正常情况下的性别 */
    @BindView(R.id.tv_normal_sex)
    TextView tv_normal_sex;
    /** 正常情况下的手机号 */
    @BindView(R.id.tv_normal_number)
    TextView tv_normal_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_percenter;
    }

    public void showView(){
        this.setTitle(head_percenter,"个人中心");
        this.setLeft(head_percenter);
    }

    @Override
    public void onClick(View v) {

    }
}
