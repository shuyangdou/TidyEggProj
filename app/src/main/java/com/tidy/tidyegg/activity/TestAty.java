package com.tidy.tidyegg.activity;

import android.os.Bundle;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by qiang on 16/10/13.
 * 测试
 */
public class TestAty extends BaseActivity {
    @BindView(R.id.imageView)
    RoundedImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView.setBackgroundResource(R.mipmap.test_goods);
    }
}
