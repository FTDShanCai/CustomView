package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyScrollView extends FrameLayout {

    private int mNestedYOffset = 0;

    public MyScrollView(@NonNull Context context) {
        this(context, null);
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float down_Y = -1;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("ftd","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ftd","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int actionMasked = event.getActionMasked();

        MotionEvent vtex = MotionEvent.obtain(event);

        if (actionMasked == MotionEvent.ACTION_DOWN) {
            mNestedYOffset = 0;
        }

        vtex.offsetLocation(0, mNestedYOffset);

        Log.d("ftd", "MotionEvent: " + actionMasked);

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                if (down_Y == -1) {
                    down_Y = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (down_Y != -1) {
                    scrollBy(0, (int) (down_Y - event.getRawY()));
                    down_Y = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                down_Y = -1;
                break;
        }
        return true;
    }
}
