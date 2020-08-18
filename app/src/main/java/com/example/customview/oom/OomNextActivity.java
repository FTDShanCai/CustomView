package com.example.customview.oom;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.customview.R;

public class OomNextActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom_next);
        iv = findViewById(R.id.img);
        Glide.with(this)
                .load(R.mipmap.large_img)
                .into(iv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Glide.get(getApplicationContext()).clearMemory();

    }
}