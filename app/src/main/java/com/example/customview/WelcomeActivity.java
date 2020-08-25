package com.example.customview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.ddc.guide.prefs.PreferencesManager;
import com.ddc.guide.shape.Focus;
import com.ddc.guide.shape.FocusGravity;
import com.ddc.guide.shape.ShapeType;
import com.ddc.guide.view.MaterialIntroView;
import com.example.customview.anim.AnimMainActivity;
import com.example.customview.lottie.LottieActivity;


public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        click(R.id.tv_1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        click(R.id.tv_2, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        click(R.id.tv_3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LottieActivity.class);
                startActivity(intent);
            }
        });
        click(R.id.tv_4, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AnimMainActivity.class);
                startActivity(intent);
            }
        });

        click(R.id.btn_show, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PreferencesManager manager = new PreferencesManager(WelcomeActivity.this);
                manager.reset("intro_card");

                new MaterialIntroView.Builder(WelcomeActivity.this)
                        .enableDotAnimation(false)
                        .enableIcon(true)
                        .setFocusGravity(FocusGravity.CENTER)
                        .setFocusType(Focus.MINIMUM)
                        .setDelayMillis(500)
                        .setShape(ShapeType.RECTANGLE)
                        .enableFadeAnimation(true)
                        .performClick(false)
                        .setMaskColor(Color.argb(180, 0, 0, 0))
                        .setTargetPadding(0)
                        .setInfoText("Hi There! Click this card and see what happens.")
                        .setTarget(findViewById(R.id.tv_3))
                        .setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
                        .show();
            }
        });


    }

    private void click(@IdRes int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }
}

