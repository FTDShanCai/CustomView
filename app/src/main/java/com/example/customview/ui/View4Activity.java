package com.example.customview.ui;

import android.os.Bundle;
import android.view.View;

import com.example.customview.R;
import com.example.customview.anim.Rotate3dAnimation;
import com.example.customview.view.MyView5;

import androidx.appcompat.app.AppCompatActivity;

public class View4Activity extends AppCompatActivity {

    private MyView5 view5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view4);

        view5 = findViewById(R.id.img);
        findViewById(R.id.复古).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view5.复古();
            }
        });

        findViewById(R.id.黑白).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view5.黑白();
            }
        });


        findViewById(R.id.曝光).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view5.曝光();
            }
        });

        findViewById(R.id.原图).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view5.原图();
            }
        });

        findViewById(R.id.D3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float centerX = view5.getWidth() / 2.0f;
                final float centerY = view5.getHeight() / 2.0f;
                Rotate3dAnimation animation = new Rotate3dAnimation(View4Activity.this, 0, 360, centerX, centerY, 1500f, false);
                animation.setDuration(3000);
                animation.setFillAfter(true);
                view5.startAnimation(animation);
            }
        });
    }
}
