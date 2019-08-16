package com.example.customview.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.customview.R;
import com.example.customview.view.MyTitleBarBehavior;
import com.google.android.material.appbar.AppBarLayout;

public class BehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);



    }
}
