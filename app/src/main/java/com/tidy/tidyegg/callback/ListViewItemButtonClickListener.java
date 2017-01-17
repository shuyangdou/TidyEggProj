package com.tidy.tidyegg.callback;

import android.view.View;

/**
 * Created by dou on 2016/10/14.
 * item 按钮点击事件
 */
public interface ListViewItemButtonClickListener {
    void onClick(View item, View widget, int position, int which);
}
