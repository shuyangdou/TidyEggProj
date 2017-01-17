package com.tidy.tidyegg.callback;

import android.view.View;

/**
 * Created by qiang on 16/10/14.
 * ListView item控件点击事件回调
 */
public interface OnItemViewClickListener {
    void onClick(View item, View widget, int position, int which);
}
