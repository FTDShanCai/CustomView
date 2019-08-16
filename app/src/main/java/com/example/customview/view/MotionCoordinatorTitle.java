package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class MotionCoordinatorTitle extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {
    public MotionCoordinatorTitle(Context context) {
        super(context);
    }

    public MotionCoordinatorTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MotionCoordinatorTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if (appBarLayout.getTotalScrollRange() != 0) {
            float v = -i * 1f / appBarLayout.getTotalScrollRange();
            Log.d("ftd", "onOffsetChanged" + v);
            setProgress(v);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) getParent();
            appBarLayout.addOnOffsetChangedListener(this);
        }
    }
}
