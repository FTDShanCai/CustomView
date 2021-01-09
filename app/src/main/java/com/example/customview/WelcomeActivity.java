package com.example.customview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.ddc.guide.custom.ShapeRenCai;
import com.ddc.guide.custom.ShapeStudy;
import com.ddc.guide.prefs.PreferencesManager;
import com.ddc.guide.shape.GuideView;
import com.ddc.guide.shape.TargetShape;
import com.ddc.guide.view.MaterialIntroView;
import com.example.customview.anim.AnimMainActivity;
import com.example.customview.lottie.LottieActivity;


public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        View tv3 = findViewById(R.id.tv_3);
        View tv4 = findViewById(R.id.tv_4);
        click(R.id.tv_1, v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.splash_anim_pop_in, R.anim.splash_anim_pop_out);
        });
        click(R.id.tv_2, v -> {
            Intent intent = new Intent(WelcomeActivity.this, NewActivity.class);
            startActivity(intent);
        });

        click(R.id.tv_3, v -> {
            Intent intent = new Intent(WelcomeActivity.this, LottieActivity.class);
            startActivity(intent);
        });
        click(R.id.tv_4, v -> {
            Intent intent = new Intent(WelcomeActivity.this, AnimMainActivity.class);
            startActivity(intent);
        });

        click(R.id.btn_show, v -> {
            PreferencesManager manager = new PreferencesManager(WelcomeActivity.this);
            manager.reset("intro_card");

            GuideView.Builder builder1
                    = new GuideView.Builder();
            GuideView.Builder builder2
                    = new GuideView.Builder();
            builder1.addTargetShape(new TargetShape.Builder(tv4)
                    .setShape(new ShapeRenCai())
                    .build());
            builder2.addTargetShape(new TargetShape.Builder(tv3)
                    .setShape(new ShapeStudy())
                    .build());
            new MaterialIntroView.Builder(WelcomeActivity.this)
                    .setDelayMillis(500)
                    .enableFadeAnimation(true)
                    .setMaskColor(Color.argb(180, 0, 0, 0))
                    .addGuideView(builder1.build())
                    .addGuideView(builder2.build())
                    .setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
                    .show();
        });
    }

    private void click(@IdRes int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }
}

