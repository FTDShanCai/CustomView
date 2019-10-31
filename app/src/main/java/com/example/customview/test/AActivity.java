package com.example.customview.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.customview.R;
import com.example.customview.bus.LiveDataBus;

public class AActivity extends AppCompatActivity {

    TextView tv_content;
    ABCViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("ftd", "A onCreate");
        tv_content = findViewById(R.id.tv_content);
        viewModel = ViewModelProviders.of(this).get(ABCViewModel.class);
        Log.d("ftd", "hashCode:" + viewModel.hashCode());

        LiveDataBus.get().getChannel("a", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tv_content.setText(s);
                    }
                });
    }

    public void fenSan(View view) {
        Intent intent = new Intent(this, BActivity.class);
        startActivity(intent);
    }

}
