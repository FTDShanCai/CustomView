package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public abstract class BaseView extends View {
    protected int mwidth;
    protected int mheight;


    public BaseView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mwidth = MeasureSpec.getSize(widthMeasureSpec);
        mheight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mwidth, mheight);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
