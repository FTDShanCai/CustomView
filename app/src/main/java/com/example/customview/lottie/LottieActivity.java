package com.example.customview.lottie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.example.customview.R;

public class LottieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        final LottieAnimationView lottie_view = findViewById(R.id.lottie_view);
        lottie_view.setAnimation("data.json");
        lottie_view.setImageAssetsFolder("images/");
        lottie_view.setRenderMode(RenderMode.SOFTWARE);
        lottie_view.setRepeatCount(-1);
        lottie_view.setSpeed(0.2f);
        lottie_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lottie_view.isAnimating()) {
                    lottie_view.pauseAnimation();
                } else {
                    lottie_view.playAnimation();
                }
            }
        });

    }
}