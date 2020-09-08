package com.example.customview.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.customview.R;
import com.example.customview.hilt.bean.Tools;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Hilt2Activity extends AppCompatActivity {

    @Inject
    Tools tools;
    @Inject
    Tools tools2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt2);
//        if (tools != null) {
//            Log.d("ftd", "tools : " + tools.getName() + " hasCode:" + tools.hashCode());
//            Log.d("ftd", "tools2 : " + tools2.getName() + " hasCode:" + tools2.hashCode());
//        } else {
//            Log.d("ftd", "tools2 inject failed.");
//        }

        MyPresenter presenter = new MyPresenter();
        presenter.init();
    }
}