package com.tidy.tidyegg.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author
 * @version 创建时间：2015-3-13下午1:23:32
 * @description 类描述：重写ViewPager,控制ViewPager是否可以左右滑动
 */
public class MyViewPager extends ViewPager {

    /**
     * 控制是否可以滑动
     */
    private boolean isLocked;

    public MyViewPager(Context context) {
        super(context);
        isLocked = false;
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isLocked) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isLocked) {
            try {
                return super.onTouchEvent(event);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void toggleLock() {
        isLocked = !isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

}
