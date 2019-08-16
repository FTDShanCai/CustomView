package com.example.customview.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.customview.DpUtils;
import com.example.customview.R;

/**
 * Created by cyandev on 2016/12/14.
 */
public class HeaderFloatBehavior2 extends CoordinatorLayout.Behavior<View> {

    private ArgbEvaluator argbEvaluator;
    Context context;

    //px
    float actionBarHeight = 0;
    float dataY;

    public HeaderFloatBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        argbEvaluator = new ArgbEvaluator();

        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (child instanceof LinearLayout) {
            return true;
        }
        return false;
    }

    float initRecycleLoca;

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float progress = 0;

        if (dataY == 0) {
            //搜索框从初始位置到达目标位置所需移动的距离
            dataY = DpUtils.dp2px(context, 250) - actionBarHeight / 2;
            //依赖视图的初始位置
            initRecycleLoca = dependency.getY();
        }
        //依赖视图的实时移动距离
        float dy = Math.abs(initRecycleLoca - dependency.getY());
        if (dy < dataY) {
            progress = 1.0f - dy / dataY;  //进度
        }
        Log.d("progress", "progress|" + progress);
        // Marginss
        final int margin = (int) (DpUtils.dp2px(context, 12) + (int) (DpUtils.dp2px(context, 45) * (1 - progress)));
        Log.d("ftd", "margin|" + margin);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.setMargins(margin, 0, (int) DpUtils.dp2px(context, 12), 0);
        child.setLayoutParams(lp);

        // Translation
        final int translateY = (int) (DpUtils.dp2px(context, 250) * (1 - progress));//dp
        Log.d("Ad", "translateY|" + translateY);
        child.setTranslationY(translateY);

        return true;
    }

}
