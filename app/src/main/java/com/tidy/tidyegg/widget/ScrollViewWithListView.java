package com.tidy.tidyegg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by qiang on 16/10/14.
 * ScrollView嵌套ListView，ListView无法展示全部内容问题
 */
public class ScrollViewWithListView extends ListView {
    public ScrollViewWithListView(Context context) {
        super(context);
    }

    public ScrollViewWithListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewWithListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
