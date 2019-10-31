package com.example.customview.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.example.customview.R;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewViewActivity extends AppCompatActivity {

    CircularRevealCardView circular_reveal_card_view;
    FloatingActionButton fab;
    ImageView iv_image;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_view);
        initViews();
        initEvent();
    }

    private void initViews() {
        circular_reveal_card_view = findViewById(R.id.circular_reveal_card_view);
        iv_image = findViewById(R.id.iv_image);
        fab = findViewById(R.id.fab);

        animationDrawable = (AnimationDrawable) iv_image.getDrawable();


        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

    }

    Handler handler = new Handler(Looper.getMainLooper());

    private void initEvent() {

        circular_reveal_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    CircularRevealWidget.RevealInfo revealInfo = new CircularRevealWidget.RevealInfo(0, 0, circular_reveal_card_view.getMeasuredWidth());
                    circular_reveal_card_view.setRevealInfo(revealInfo);
                    Animator animator = CircularRevealCompat.createCircularReveal(circular_reveal_card_view, 0, 0, 0);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.setDuration(2000);
                    animator.start();
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            circular_reveal_card_view.setVisibility(View.INVISIBLE);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    circular_reveal_card_view.setVisibility(View.VISIBLE);
                                }
                            }, 1000);
                        }
                    });
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(NewViewActivity.this, fab, ViewCompat.getTransitionName(fab));
                    startActivity(new Intent(NewViewActivity.this, AdvActivity.class), options.toBundle());
                }
            }
        });


    }
}
