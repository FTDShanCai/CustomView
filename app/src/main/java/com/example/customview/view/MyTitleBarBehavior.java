package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */

public class MyTitleBarBehavior extends CoordinatorLayout.Behavior<View> {
    public MyTitleBarBehavior() {
        Log.d("ftd", "MyTitleBarBehavior()");
    }

    public MyTitleBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("ftd", " MyTitleBarBehavior(Context context, AttributeSet attrs)");
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d("ftd", "onNestedPreScroll:" + dx + "|" + dy);
//        target.setTranslationY(dy);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d("asdfztd", "onNestedScroll:" + dyConsumed + "|" + dyUnconsumed);
        int total_offset_height = coordinatorLayout.getHeight() - child.getHeight();

        int offset = child.getTop() + dyConsumed;
        if (total_offset_height >= offset && offset >= 0) {
            ViewCompat.offsetTopAndBottom(child, dyConsumed);
        } else {
            if (total_offset_height - child.getTop() >= 0 && offset >= 0) {
                ViewCompat.offsetTopAndBottom(child, total_offset_height - child.getTop());
            } else if (offset < 0 && child.getTop() > 0) {
                ViewCompat.offsetTopAndBottom(child, -child.getTop());
            }
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.d("ftd", "onStartNestedScroll");
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return true;
    }
}
