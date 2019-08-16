package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class NestedScrollViewBehavior extends CoordinatorLayout.Behavior<NestedScrollView> {
    public NestedScrollViewBehavior() {
        Log.d("ftd", "NestedScrollViewBehavior()");
    }

    public NestedScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("ftd", " NestedScrollViewBehavior(Context context, AttributeSet attrs)");
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
        return dependency instanceof RelativeLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
//        ViewCompat.offsetTopAndBottom(child, dependency.getBottom());
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull NestedScrollView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d("ftd", "onNestedScroll2:" + dyConsumed + "|" + dyUnconsumed);
    }


}
