package com.tidy.tidyegg.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.tidy.tidyegg.R;
import com.tidy.tidyegg.base.ActivityManager;
import com.tidy.tidyegg.base.BaseActivity;
import com.tidy.tidyegg.fragment.CategoriesFrag;
import com.tidy.tidyegg.fragment.HomeFrag;
import com.tidy.tidyegg.fragment.MineFrag;
import com.tidy.tidyegg.fragment.ShoppingCartFrag;

import butterknife.BindView;

/**
 * Created by qiang on 2016/10/9.
 * APP首页
 */
public class MainAty extends BaseActivity {
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    // tab图片
    private int mImages[] = {
            R.drawable.sel_tabitem_home,
            R.drawable.sel_tabitem_categories,
            R.drawable.sel_tabitem_shoppingcart,
            R.drawable.sel_tabitem_mine
    };

    // tab文字
    private String mTitles[] = {
            "首页",
            "商品分类",
            "购物车",
            "我的"
    };

    // tab对应的Fragment的tag
    private String mFragmentTags[] = {
            "home",
            "categories",
            "shoppingCart",
            "mine"
    };

    // tab对应的Fragment
    private Class mFragment[] = {
            HomeFrag.class,
            CategoriesFrag.class,
            ShoppingCartFrag.class,
            MineFrag.class
    };

    @Override
    protected int getLayoutId() {
        return R.layout.aty_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);// 去除分割线

        for (int i = 0; i < mImages.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getView(i));
            mTabHost.addTab(tabSpec, mFragment[i], null);
        }
    }

    private View getView(int index) {
        View view = getLayoutInflater().inflate(R.layout.aty_main_tabitem, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabItem_image);
        imageView.setImageResource(mImages[index]);
        TextView textView = (TextView) view.findViewById(R.id.tabItem_text);
        textView.setText(mTitles[index]);
        return view;
    }

    private long currentTime;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentTime < 2000) {
            ActivityManager.getAppManager().AppExit(MainAty.this);
        } else {
            toast("再按一次退出程序");
        }
        currentTime = System.currentTimeMillis();
    }
}
