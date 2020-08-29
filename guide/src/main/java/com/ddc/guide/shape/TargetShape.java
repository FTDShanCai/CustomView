package com.ddc.guide.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;

import com.ddc.guide.target.Target;
import com.ddc.guide.target.ViewTarget;
import com.ddc.guide.view.MaterialIntroView;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/8/28 0028
 * description:xxx
 * ******************************
 */
public class TargetShape {

    private RectF adjustedRect;
    private final Target target;
    private final int paddingTop;
    private final int paddingBottom;
    private final int paddingLeft;
    private final int paddingRight;
    private Shape shape;

    private TargetShape(Builder builder) {
        target = new ViewTarget(builder.targetView);
        paddingTop = builder.paddingTop;
        paddingBottom = builder.paddingBottom;
        paddingLeft = builder.paddingLeft;
        paddingRight = builder.paddingRight;
        shape = builder.shape;
        calculateAdjustedRect();
    }

    public void draw(Canvas canvas, Paint paint) {
        if (canvas == null || paint == null) return;
        canvas.drawRoundRect(adjustedRect, 0, 0, paint);
    }

    public void initViewShape(MaterialIntroView view) {
        if (shape == null) return;

        View targetView = target.getView();
        int[] screen_loc = new int[2];
        targetView.getLocationOnScreen(screen_loc);
        int x = screen_loc[0];
        int y = screen_loc[1];
        View placeHolder = new View(view.getContext());
        placeHolder.setId(ViewCompat.generateViewId());
        placeHolder.setBackgroundColor(Color.argb(0, 0, 0, 0));
        view.addView(placeHolder);
        ViewGroup.LayoutParams layoutParams = placeHolder.getLayoutParams();
        layoutParams.width = targetView.getWidth();
        layoutParams.height = targetView.getHeight();

        {
            ConstraintSet set = new ConstraintSet();
            set.clone(view);
            set.connect(placeHolder.getId(), ConstraintSet.TOP, view.getId(), ConstraintSet.TOP);
            set.setMargin(placeHolder.getId(), ConstraintSet.LEFT, x);
            set.setMargin(placeHolder.getId(), ConstraintSet.TOP, y);

            set.applyTo(view);
        }
        shape.configShape(view, placeHolder.getId());
    }


    public Target getTarget() {
        return target;
    }

    private void calculateAdjustedRect() {
        RectF rect = new RectF();
        rect.set(target.getRect());
        rect.left -= paddingLeft;
        rect.top -= paddingTop;
        rect.right += paddingRight;
        rect.bottom += paddingBottom;
        adjustedRect = rect;
    }

    public static class Builder {

        private int paddingTop;
        private int paddingBottom;
        private int paddingLeft;
        private int paddingRight;
        private Shape shape;

        private @NonNull
        View targetView;

        public Builder(@NonNull View targetView) {
            this.targetView = targetView;
        }

        //<editor-fold desc="padding">
        public Builder setPadding(int left, int top, int right, int bottom) {
            this.paddingTop = top;
            this.paddingBottom = bottom;
            this.paddingLeft = left;
            this.paddingRight = right;
            return this;
        }

        public Builder setPaddingTop(int top) {
            this.paddingTop = top;
            return this;
        }

        public Builder setPaddingBottom(int bottom) {
            this.paddingBottom = bottom;
            return this;
        }

        public Builder setPaddingLeft(int left) {
            this.paddingLeft = left;
            return this;
        }

        public Builder setPaddingRight(int right) {
            this.paddingRight = right;
            return this;
        }
        //</editor-fold>

        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }


        public TargetShape build() {
            return new TargetShape(this);
        }
    }
}
