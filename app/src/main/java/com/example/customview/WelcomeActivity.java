package com.example.customview;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


    }

    private void click(@IdRes int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }
}

