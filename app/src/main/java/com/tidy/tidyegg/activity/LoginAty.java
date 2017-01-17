package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.ActivityManager;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.bean.MineAddBean;
import com.tidy.tidyegg.utils.SpUtils;
import com.tidy.tidyegg.utils.ValueUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by qiang on 2016/10/8.
 * 登录界面
 */
public class LoginAty extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ed_account)
    EditText ed_account; //输入账号
    @BindView(R.id.ed_password)
    EditText ed_password; //输入密码
    @BindView(R.id.cb_password)
    CheckBox cb_password; //勾选记住密码
    @BindView(R.id.tv_forgot)
    TextView tv_forgot; //点击忘记密码
    @BindView(R.id.btn_login)
    Button btn_login; //登录按钮
    private boolean isCheck;
    private boolean checked;//sp存储结果
    private String account;//账号
    private String login_pass;//密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        checked = SpUtils.getSharePreBoolean(LoginAty.this, "Login_Checked", "isChecked");
        if (checked) {
            String isAccount = SpUtils.getSharePreStr(LoginAty.this, "Login_Account", "isAccount");
            String isPass = SpUtils.getSharePreStr(LoginAty.this, "Login_Pass", "isPass");
            ed_account.setText(isAccount);
            ed_password.setText(isPass);
        }
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_login;
    }

    public void showView() {
        tv_forgot.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        cb_password.setChecked(checked);
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isCheck = true;
                    cb_password.setChecked(isCheck);
                } else {
                    isCheck = false;
                    cb_password.setChecked(isCheck);
                }
                SpUtils.putSharePre(LoginAty.this, "Login_Checked", "isChecked", isCheck);
            }
        });
        ed_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                account = ed_account.getText().toString();
            }
        });

        ed_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                login_pass = ed_password.getText().toString();
            }
        });
        account = ed_account.getText().toString();
        login_pass = ed_password.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /** 忘记密码 */
            case R.id.tv_forgot:
                startActivity(ForgotPasswordAty.class);
                break;
            /** 登录 */
            case R.id.btn_login:
                passContrast();
                break;
        }
    }
    /**
     * 校验账号密码
     */
    public void passContrast() {
        if (ValueUtils.isStrNotEmpty(account) && ValueUtils.isStrNotEmpty(login_pass)) {
            if (account.equals("18915581676") && login_pass.equals("123456")) {
                SpUtils.putSharePre(LoginAty.this, "Login_Account", "isAccount", account);
                SpUtils.putSharePre(LoginAty.this, "Login_Pass", "isPass", login_pass);
                MineAddBean addBean1 = new MineAddBean();
                addBean1.setName("李先生");
                addBean1.setPhone("18915581676");
                addBean1.setAdd("上海市虹口区广济路766号B栋-503");
                addBean1.setMo("yes");
                SpUtils.putObject(mContext,"ADD_MOREN","MOREN",addBean1);
                startActivity(MainAty.class);
            } else {
                toast("账号或者密码错误，请重新输入！");
            }
        } else {
            toast("请输入账号密码");
        }
    }
    private long currentTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime < 2000) {
            ActivityManager.getAppManager().AppExit(mContext);
        } else {
            toast("再按一次退出程序");
        }
        currentTime = System.currentTimeMillis();
    }
}
