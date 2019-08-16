package com.example.customview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.customview.R;
import com.example.customview.view.CreditMainView;

import androidx.appcompat.app.AppCompatActivity;

public class GestureActivity extends AppCompatActivity {

    CreditMainView credit_view;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        credit_view = findViewById(R.id.credit_view);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit_view.setData(432, 500, "", "");
            }
        });

    }
}
