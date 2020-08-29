package com.ddc.guide.shape;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ddc.guide.view.MaterialIntroView;

import java.util.ArrayList;
import java.util.List;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/8/25 0025
 * description:xxx
 * ******************************
 */
public class GuideView {

    private List<TargetShape> targetShapes = new ArrayList<>();

    private ConstraintLayout rootView;

    private GuideView(Builder builder) {
        this.targetShapes = builder.targetShapes;
    }

    public void initRootView(ConstraintLayout rootView) {
        this.rootView = rootView;
    }

    public final void showTipView(MaterialIntroView view, TargetShape targetShape) {
//        showTip(view, targetShape);
    }

    @NonNull
    public List<TargetShape> getTargetShapes() {
        return targetShapes;
    }

    public static class Builder {

        private List<TargetShape> targetShapes;

        public Builder addTargetShape(TargetShape shape) {
            if (targetShapes == null) {
                targetShapes = new ArrayList<>();
            }
            targetShapes.add(shape);
            return this;
        }

        public GuideView build() {
            return new GuideView(this);
        }
    }
}
