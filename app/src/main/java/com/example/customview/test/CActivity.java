package com.example.customview.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;

public class CActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        Log.d("ftd", "C onCreate");
    }
    public void fenSan(View view) {
        Intent intent = new Intent(this, AActivity.class);
        startActivity(intent);
    }
}
