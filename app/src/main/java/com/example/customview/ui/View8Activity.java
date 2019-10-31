package com.example.customview.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;
import com.example.customview.view.Tools;
import com.example.customview.view.ViewSizeChangeAnimation;

public class View8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view8);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoneWindow(View8Activity.this);
            }
        });
    }


    private void getPhoneWindow(final Activity context) {
        View view = context.getWindow().getDecorView();
        if (view instanceof FrameLayout) {
            Log.d("ftd", "ok");
            final FrameLayout decorView = (FrameLayout) view;

            final View animView = LayoutInflater.from(context).inflate(R.layout.dialog_credit_point, null);
            TextView baoyangCredit = animView.findViewById(R.id.baoyang_credit);
            final FrameLayout baoyangAnimFl = animView.findViewById(R.id.baoyang_anim_fl);
            final Animation animations1, animations2, animations3, animations4;

            baoyangCredit.setText("信用分" + "+" + "15");
            animations1 = AnimationUtils.loadAnimation(context, R.anim.baoyang_in);
            if (TextUtils.isEmpty("desc")) {
                animations2 = new ViewSizeChangeAnimation(baoyangAnimFl, Tools.dp2px(context, 38), Tools.dp2px(context, 86));
            } else {
                animations2 = new ViewSizeChangeAnimation(baoyangAnimFl, Tools.dp2px(context, 38), Tools.dp2px(context, 140));
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) baoyangCredit.getLayoutParams();
                layoutParams.width = Tools.dp2px(context, 102);
                baoyangCredit.setLayoutParams(layoutParams);
            }
            animations2.setDuration(500);
            animations4 = AnimationUtils.loadAnimation(context, R.anim.baoyang_exit);
            animations3 = new ViewSizeChangeAnimation(baoyangAnimFl, Tools.dp2px(context, 38), Tools.dp2px(context, 38));
            animations3.setStartOffset(1000);
            animations3.setDuration(500);
            animations1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    baoyangAnimFl.startAnimation(animations2);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            animations2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    baoyangAnimFl.startAnimation(animations3);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            animations3.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    baoyangAnimFl.startAnimation(animations4);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            animations4.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //判断是否获得车辆档案100%完善
                    Toast.makeText(context, "完成", Toast.LENGTH_LONG).show();
                    decorView.removeView(animView);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            decorView.addView(animView);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) animView.getLayoutParams();
            layoutParams.gravity = Gravity.CENTER;
            baoyangAnimFl.setVisibility(View.VISIBLE);
            baoyangAnimFl.startAnimation(animations1);
        }

    }
}
