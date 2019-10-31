package com.example.customview.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * <pre>
 *     author : jiaam
 *     time   : 2019/04/01
 *     desc   :保养更新里程动画
 * </pre>
 */

public class ViewSizeChangeAnimation extends Animation {
    int initialHeight;
    int targetHeight;
    int initialWidth;
    int targetWidth;
    View view;

    public ViewSizeChangeAnimation(View view, int targetHeight, int targetWidth) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().height = initialHeight + (int) ((targetHeight - initialHeight) * interpolatedTime);
        view.getLayoutParams().width = initialWidth + (int) ((targetWidth - initialWidth) * interpolatedTime);
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        this.initialHeight = height;
        this.initialWidth = width;
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

}
