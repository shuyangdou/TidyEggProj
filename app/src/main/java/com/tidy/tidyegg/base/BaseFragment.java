package com.tidy.tidyegg.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.callback.OnClickListener;
import com.tidy.tidyegg.utils.ToastUtils;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiang on 2016/10/8.
 * Fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private View rootView;

    /**
     * ButterKnife解绑类
     */
    private Unbinder unbinder;

    /**
     * double精确到小数点后两位
     */
    public final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            if (rootView == null) {
                rootView = inflater.inflate(layoutId, container, false);
                unbinder = ButterKnife.bind(this, rootView);// ButterKnife绑定

                // 初始化数据
                initData();
            }
        }

        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，否则会发生这个rootView已经有parent的错误
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();// ButterKnife解绑
    }

    /**
     * 布局文件ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取Context
     */
    public Context getContext() {
        return mContext;
    }

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
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * 设置标题栏右侧文字
     *
     * @param view
     * @param content 文字内容
     */
    public void setTitleRight(View view, String content, final OnClickListener listener) {
        TextView tv_titleRight = (TextView) view.findViewById(R.id.appTitle_rightText);
        tv_titleRight.setText(content);
        tv_titleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
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
        intent.setClass(mContext, cls);
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
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
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
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
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
        ToastUtils.ToastMessage(getActivity(), msg);
    }

    public void showD(Dialog dialog) {
        if (!getActivity().isFinishing()) {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    public void dismissD(Dialog dialog) {
        if (!getActivity().isFinishing()) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

}
