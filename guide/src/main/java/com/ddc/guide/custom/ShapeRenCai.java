package com.ddc.guide.custom;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
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
public class ShapeRenCai implements Shape {

    @Override
    public void configShape(MaterialIntroView group, int targetViewId) {
        Context context = group.getContext();
        TextView textTip = new TextView(context);
        SpannableString spas = new SpannableString("新增人才模块,快来加入计划吧");
        spas.setSpan(new AbsoluteSizeSpan(Utils.dpToPx(28)), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
            set.connect(textTip.getId(), ConstraintSet.TOP,
                    targetViewId, ConstraintSet.BOTTOM);
            set.connect(textTip.getId(), ConstraintSet.LEFT,
                    targetViewId, ConstraintSet.LEFT);
            set.connect(textTip.getId(), ConstraintSet.RIGHT,
                    targetViewId, ConstraintSet.RIGHT);
            set.setMargin(textTip.getId(), ConstraintSet.TOP, Utils.dpToPx(20));
            set.applyTo(group);
        }

        TextView tvConfirm = new TextView(context);
        tvConfirm.setText("我知道了");
        tvConfirm.setTextSize(16);
        tvConfirm.setTextColor(Color.WHITE);
        tvConfirm.setPadding(25, 13, 25, 13);
        tvConfirm.setGravity(Gravity.CENTER);
        tvConfirm.setBackgroundResource( R.drawable.shap_confirm);
        tvConfirm.setId(ViewCompat.generateViewId());
        tvConfirm.setOnClickListener(v -> group.dismiss());
        group.addView(tvConfirm);

        {
            ConstraintSet set = new ConstraintSet();
            set.clone(group);
            set.connect(tvConfirm.getId(), ConstraintSet.TOP,
                    textTip.getId(), ConstraintSet.BOTTOM);
            set.connect(tvConfirm.getId(), ConstraintSet.LEFT,
                    textTip.getId(), ConstraintSet.LEFT);
            set.connect(tvConfirm.getId(), ConstraintSet.RIGHT,
                    textTip.getId(), ConstraintSet.RIGHT);
            set.setMargin(tvConfirm.getId(), ConstraintSet.TOP, Utils.dpToPx(20));
            set.applyTo(group);
        }
    }
}
