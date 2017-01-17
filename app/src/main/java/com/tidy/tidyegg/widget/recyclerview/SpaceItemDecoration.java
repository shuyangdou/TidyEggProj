package com.tidy.tidyegg.widget.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qiang on 16/10/15.
 * RecyclerView设置LinearLayoutManager和GridLayoutManager的间距
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int topSpace;
    private int bottomSpace;
    private int leftSpace;
    private int rightSpace;

    /**
     * 构造方法
     *
     * @param topSpace    距离上方的间距
     * @param bottomSpace 距离下方的间距
     * @param leftSpace   距离左方的间距
     * @param rightSpace  距离右方的间距
     */
    public SpaceItemDecoration(int topSpace, int bottomSpace, int leftSpace, int rightSpace) {
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = topSpace;
        outRect.bottom = bottomSpace;
        outRect.left = leftSpace;
        outRect.right = rightSpace;
    }
}
