package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.utils.ValueUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/9.
 * 设置新密码
 */
public class ReinstallAty extends BaseActivity{

    @BindView(R.id.head_reinstall)
    RelativeLayout head_reinstall;//title标题
    @BindView(R.id.ed_newpassword)
    EditText ed_newpassword;//输入新密码
    @BindView(R.id.ed_commit_newpassword)
    EditText ed_commit_newpassword;//确认新密码
    @BindView(R.id.btn_commit)
    Button btn_commit;//确认提交
    private String newpassword;
    private String commit_newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_reinstall;
    }

    public void showView(){
        this.setTitle(head_reinstall, "设置新密码");
        this.setLeft(head_reinstall);

        btn_commit.setOnClickListener(new View.OnClickListener() {//确定按钮
            @Override
            public void onClick(View v) {
                if (ValueUtils.isStrNotEmpty(newpassword)&&ValueUtils.isStrNotEmpty(commit_newpassword)){
                    if (newpassword.equals(commit_newpassword)){
                        commitPassword();
                    }else{
                        toast("两次输入密码不一致，请重新输入！");
                    }
                }else{
                    toast("新密码不能为空");
                }
            }
        });
        ed_newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                newpassword =  ed_newpassword.getText().toString();
            }
        });

        ed_commit_newpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                commit_newpassword  =  ed_commit_newpassword.getText().toString();
            }
        });
    }

    public void commitPassword(){

    }
}
