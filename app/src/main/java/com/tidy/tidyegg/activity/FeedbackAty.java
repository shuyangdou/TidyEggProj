package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/14.
 * 意见反馈
 */
public class FeedbackAty extends BaseActivity{

    @BindView(R.id.head_feedback)
    RelativeLayout head_feedback;
    @BindView(R.id.ed_feedback)
    EditText ed_feedback;
    @BindView(R.id.btn_feedback)
    Button btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_feedback;
    }

    public void showView(){
        this.setTitle(head_feedback,"反馈意见");
        this.setLeft(head_feedback);
    }
}
