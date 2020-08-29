package com.ddc.guide.custom;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;

import com.ddc.guide.R;
import com.ddc.guide.shape.Shape;
import com.ddc.guide.utils.Utils;
import com.ddc.guide.view.MaterialIntroView;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/8/29 0029
 * description:xxx
 * ******************************
 */
public class ShapeStudy implements Shape {

    @Override
    public void configShape(MaterialIntroView group, int targetViewId) {
        Context context = group.getContext();
        TextView textTip = new TextView(context);
        SpannableString spas = new SpannableString("这里是学习模块");
        spas.setSpan(new AbsoluteSizeSpan(Utils.dpToPx(28)), 3, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textTip.setText(spas);
        textTip.setTextColor(Color.WHITE);
        textTip.setTextSize(16);
        textTip.setPadding(40, 8, 40, 10);
        textTip.setId(ViewCompat.generateViewId());
        textTip.setBackgroundResource(R.drawable.shape_tip);
        group.addView(textTip);

        {
            ConstraintSet set = new ConstraintSet();
            set.clone(group);
            set.connect(textTip.getId(), ConstraintSet.BOTTOM,
                    targetViewId, ConstraintSet.TOP);
            set.connect(textTip.getId(), ConstraintSet.LEFT,
                    targetViewId, ConstraintSet.LEFT);
            set.setMargin(textTip.getId(), ConstraintSet.BOTTOM, Utils.dpToPx(10));
            set.setMargin(textTip.getId(), ConstraintSet.LEFT, Utils.dpToPx(5));
            set.applyTo(group);
        }
    }
}
