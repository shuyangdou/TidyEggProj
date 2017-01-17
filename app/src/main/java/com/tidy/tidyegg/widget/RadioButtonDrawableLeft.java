package com.tidy.tidyegg.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by qiang on 16/10/12.
 * 设置RadioButton的drawableLeft与文本一起居中显示，此时布局文件中的RadioButton不能设置android:gravity="center"
 */
public class RadioButtonDrawableLeft extends RadioButton {
    public RadioButtonDrawableLeft(Context context) {
        super(context);
    }

    public RadioButtonDrawableLeft(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioButtonDrawableLeft(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取设置的图片
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            // 第一个是left
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                // 获取文字的宽度
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
