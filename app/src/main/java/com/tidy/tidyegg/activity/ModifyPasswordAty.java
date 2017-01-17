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
 * Created by DOU on 2016/10/14.
 * 修改密码
 */
public class ModifyPasswordAty extends BaseActivity{

    @BindView(R.id.head_modifypass)
    RelativeLayout head_modifypass;
    @BindView(R.id.ed_oldpass)
    EditText ed_oldpass;
    @BindView(R.id.ed_newpass)
    EditText ed_newpass;
    @BindView(R.id.ed_commit_pass)
    EditText ed_commit;
    @BindView(R.id.btn_modifypass)
    Button btn_modifypass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_modifypassword;
    }

    public void showView(){
        this.setLeft(head_modifypass);
        this.setTitle(head_modifypass,"修改密码");
    }
}
