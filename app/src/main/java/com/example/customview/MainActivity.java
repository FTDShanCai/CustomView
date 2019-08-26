package com.example.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customview.ui.BehaviorActivity;
import com.example.customview.ui.ConstraintLayoutActivity;
import com.example.customview.ui.GestureActivity;
import com.example.customview.ui.JustifyTextActivity;
import com.example.customview.ui.LithoActivity;
import com.example.customview.ui.MotionCoordinatorActivity;
import com.example.customview.ui.MotionLayoutActivity;
import com.example.customview.ui.NewBehaviorActivity;
import com.example.customview.ui.NewViewActivity;
import com.example.customview.ui.PointViewActivity;
import com.example.customview.ui.SharedElement1Activity;
import com.example.customview.ui.TestActivity;
import com.example.customview.ui.View1Activity;
import com.example.customview.ui.View2Activity;
import com.example.customview.ui.View3Activity;
import com.example.customview.ui.View4Activity;
import com.example.customview.ui.View5Activity;
import com.example.customview.ui.View6Activity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View1Activity.class));
            }
        });

        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View2Activity.class));
            }
        });

        findViewById(R.id.view3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View3Activity.class));
            }
        });

        findViewById(R.id.view4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View4Activity.class));
            }
        });
        findViewById(R.id.view5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View5Activity.class));
            }
        });
        findViewById(R.id.view6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PointViewActivity.class));
            }
        });

        findViewById(R.id.view7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GestureActivity.class));
            }
        });
        findViewById(R.id.view8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
        findViewById(R.id.view9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LithoActivity.class));
            }
        });
        findViewById(R.id.view10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BehaviorActivity.class));
            }
        });
        findViewById(R.id.view11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View6Activity.class));
            }
        });
        findViewById(R.id.view12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JustifyTextActivity.class));
            }
        });
        findViewById(R.id.view13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MotionLayoutActivity.class));
            }
        });
        findViewById(R.id.view14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MotionCoordinatorActivity.class));
            }
        });

        findViewById(R.id.view15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewViewActivity.class));
            }
        });
        findViewById(R.id.view16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharedElement1Activity.class));
            }
        });
        findViewById(R.id.view17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
            }
        });
        findViewById(R.id.view18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewBehaviorActivity.class));
            }
        });
    }
}
