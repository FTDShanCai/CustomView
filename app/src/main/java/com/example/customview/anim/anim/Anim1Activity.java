package com.example.customview.anim.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.example.customview.R;

/**
 * 淡入淡出
 */
public class Anim1Activity extends AppCompatActivity {

    int animTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);
    private ProgressBar progressBar;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim1);

        Button btn = findViewById(R.id.btn);
        contentView = findViewById(R.id.view);
        progressBar = findViewById(R.id.progress);
        contentView.setAlpha(0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim();
            }
        });
    }

    private void startAnim() {
        contentView.animate()
                .alpha(1)
                .setDuration(animTime)
                .setListener(null)
                .start();
        progressBar.animate()
                .alpha(0)
                .setDuration(animTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        progressBar.setVisibility(View.GONE);
                    }
                }).start();
    }
}