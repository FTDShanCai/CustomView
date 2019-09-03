package com.example.customview.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class NewBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_behavior_acitiviy);
        SmartRefreshLayout refresh =  findViewById(R.id.refresh);

        refresh.setEnableLoadMore(true);
    }
}
