package com.example.customview.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BehaviorActivity extends AppCompatActivity {
    FloatingActionButton fab;
    Button btn_true, btn_false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        Intent intent = new Intent();
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
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
