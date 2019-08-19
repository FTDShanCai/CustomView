package com.example.customview.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.customview.R;
import com.example.customview.view.MyTitleBarBehavior;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BehaviorActivity extends AppCompatActivity {
    FloatingActionButton fab;
    Button btn_true, btn_false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        fab = findViewById(R.id.fab);
        btn_true = findViewById(R.id.btn_true);
        btn_false = findViewById(R.id.btn_false);

        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fab.setExpanded(true);
            }
        });
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setExpanded(false);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
