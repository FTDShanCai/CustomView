package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MyView1 extends ViewGroup {
    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("ftd", "onLayout");
        int count = getChildCount();
        int width = r - l;
        int height = b - t;
        int vh = 0;
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                tv.setGravity(Gravity.CENTER);
            }

            int child_h = view.getMeasuredHeight();
            view.layout(l, vh, r, vh + child_h);
            vh = vh + child_h;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

}
