package com.example.customview.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;


import com.example.customview.AppUtil;
import com.example.customview.R;

import java.lang.ref.WeakReference;

/**
 * Created by cyandev on 2016/12/14.
 */
public class HeaderFloatBehavior extends CoordinatorLayout.Behavior<View> {

    private ArgbEvaluator argbEvaluator;
    Context context;

    //px
    float actionBarHeight = 0;
    float marginTop;
    float dataY;
    float initRecycleLoca;

    public HeaderFloatBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        argbEvaluator = new ArgbEvaluator();

        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        marginTop = AppUtil.getStatusHeight(context);

        Log.e("aaaa", actionBarHeight + " = actionBarHeight");
        Log.e("aaaa", marginTop + " = marginTop");
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (child instanceof LinearLayout)
            return true;
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float progress = 0;
        if (dataY == 0) {
            //搜索框从初始位置到达目标位置所需移动的距离
            dataY = AppUtil.dp2px(context, 200) - marginTop - actionBarHeight / 2;
            //依赖视图的初始位置
            initRecycleLoca = dependency.getY();
        }
        //依赖视图的实时移动距离
        float dy = initRecycleLoca - dependency.getY();
        if (dy < dataY) {
            progress = 1.0f - dy / dataY;  //进度
        }

        // Marginss
        final int margin = AppUtil.dp2px(context, 12) + (int) (AppUtil.dp2px(context, 45) * (1 - progress));
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.setMargins(margin, 0, AppUtil.dp2px(context, 12), 0);
        child.setLayoutParams(lp);

        // Translation
        final int translateY = 200 - 40 / 2;//dp
        child.setTranslationY(AppUtil.dp2px(context, translateY) * progress);

        return true;
    }

}
