package com.example.customview.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.customview.R;
import com.example.customview.particlesmasher.ParticleSmasher;
import com.example.customview.particlesmasher.SmashAnimator;
import com.example.customview.particlesmasher.Utils;

public class View6Activity extends AppCompatActivity {

    private ImageView iv_image;
    private ParticleSmasher smasher;

    private boolean isFenSui = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view6);
        iv_image = findViewById(R.id.iv_image);
        smasher = new ParticleSmasher(this);
    }

    public void fenSan(View view) {
        if (isFenSui) {
            smasher.reShowView(iv_image);
        } else {
            smasher.with(iv_image)
                    .setStyle(SmashAnimator.STYLE_FLOAT_RIGHT)    // 设置动画样式
                    .setDuration(1000)                     // 设置动画时间
                    .setStartDelay(100)                    // 设置动画前延时
                    .setHorizontalMultiple(3)              // 设置横向运动幅度，默认为3
                    .setVerticalMultiple(4)                // 设置竖向运动幅度，默认为4
                    .setParticleRadius(Utils.dp2Px(1))
                    .addAnimatorListener(new SmashAnimator.OnAnimatorListener() {
                        @Override
                        public void onAnimatorStart() {
                            super.onAnimatorStart();
                            // 回调，动画开始
                        }

                        @Override
                        public void onAnimatorEnd() {
                            super.onAnimatorEnd();
                            // 回调，动画结束
                        }
                    })
                    .start();
        }
        isFenSui = !isFenSui;
    }

}
