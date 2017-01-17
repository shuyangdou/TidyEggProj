package com.tidy.tidyegg.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiang on 2016/10/8.
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 全局的Context
     */
    protected Context mContext;

    /**
     * ButterKnife解绑类
     */
    public Unbinder unbinder;

    /**
     * double精确到小数点后两位
     */
    public final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    /**
     * double取整
     */
    public final DecimalFormat decimalFormat2 = new DecimalFormat("0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            // ButterKnife绑定
            unbinder = ButterKnife.bind(this);
        }
        mContext = this.getApplicationContext();
        // 添加Activity到堆栈
        ActivityManager.getAppManager().addActivity(this);
    }

    /**
     * 布局文件ID
     */
    protected abstract int getLayoutId();

    /**
     * 设置标题
     *
     * @param view
     * @param title 标题内容
     */
    public void setTitle(View view, String title) {
        TextView tv_title = (TextView) view.findViewById(R.id.appTitle_title);
        tv_title.setText(title);
    }

    /**
     * 返回上一界面
     *
     * @param view
     */
    public void setLeft(View view) {
        TextView tv_back = (TextView) view.findViewById(R.id.appTitle_back);
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 指示线宽度
     */
    private int width;

    /**
     * 初始化指示线
     */
    public void initImageView(TextView view, int count, int colorId) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        width = screenW / count;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(colorId));
        setNaviShow(0, view);
    }

    /**
     * 设置指示线的宽度
     */
    public void setNaviShow(int index, TextView view) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.leftMargin = index * width;
        params.width = width;
        view.setLayoutParams(params);
    }


    /**
     * 弹出Toast提示
     *
     * @param msg 提示内容
     */
    public void toast(String msg) {
        ToastUtils.ToastMessage(this, msg);
    }


    /**
     * 获取跳转过来的Bundle数据
     *
     * @return bundle
     */
    public Bundle getBundle() {
        Bundle bundle = getIntent().getExtras();
        return bundle;
    }

    /**
     * 获取全局的Context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 显示Dialog
     *
     * @param dialog
     */
    public void showD(Dialog dialog) {
        if (!this.isFinishing()) {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    /**
     * 隐藏Dialog
     *
     * @param dialog
     */
    public void dismissD(Dialog dialog) {
        if (!this.isFinishing()) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ButterKnife解绑
        if (unbinder != null)
            unbinder.unbind();

        // 结束Activity从堆栈中移除
        ActivityManager.getAppManager().finishActivity(this);
    }

}
