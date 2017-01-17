package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.utils.ValueUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dou on 2016/10/9.
 * 找回密码
 */
public class ForgotPasswordAty extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.head_forgot)
    RelativeLayout head_forgot;//title标题
    @BindView(R.id.ed_forgot_account)
    EditText ed_forgot_account;//输入手机号
    @BindView(R.id.ed_forgot_verification)
    EditText ed_forgot_verification;//输入验证码
    @BindView(R.id.btn_forgot_verification)
    Button btn_forgot_verification;//获取验证码按钮
    @BindView(R.id.tv_forgot_showtime)
    TextView tv_forgot_showtime;//倒计时
    @BindView(R.id.btn_forgot_next)
    Button btn_forgot_next;//下一步按钮
    private String account;//手机号码
    private String verification;//手机验证码
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_forgotpassword;
    }

    public void showView() {
        this.setTitle(head_forgot, "找回密码");
        this.setLeft(head_forgot);
        btn_forgot_verification.setOnClickListener(this);
        btn_forgot_next.setOnClickListener(this);
        ed_forgot_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                account = ed_forgot_account.getText().toString();
            }
        });

        ed_forgot_verification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                verification = ed_forgot_verification.getText().toString();
            }
        });

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_forgot_showtime.setText("(请在" + millisUntilFinished / 1000 + "秒内完成验证，超时请点击重新发送)");
            }

            @Override
            public void onFinish() {
                toast("验证超时，请重新获取验证码！");
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /** 获取验证码 */
            case R.id.btn_forgot_verification:
                if (ValueUtils.isStrNotEmpty(ed_forgot_account.getText().toString())){
                    timer.start();
                }else{
                    toast("请输入手机号码！");
                }

                break;

            /** 下一步 */
            case R.id.btn_forgot_next:
                if (ValueUtils.isStrNotEmpty(account) && ValueUtils.isStrNotEmpty(verification)) {
                    if (verification.equals("1111")) {
                        timer.cancel();
                        startActivity(ReinstallAty.class);
                    } else {
                        toast("验证码错误，请重新获取");
                    }
                } else {
                    toast("账号和验证码不能为空！");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
