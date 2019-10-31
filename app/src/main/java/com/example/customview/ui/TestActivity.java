package com.example.customview.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.AbilityBean;
import com.example.customview.R;
import com.example.customview.service.MyService;
import com.example.customview.view.AbilityMapView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    AbilityMapView map_view;

    private final int[][] icons = {
            {R.mipmap.ic_credit_lable_1, R.mipmap.ic_credit_lable_1_grey},
            {R.mipmap.ic_credit_lable_2, R.mipmap.ic_credit_lable_2_grey},
            {R.mipmap.ic_credit_lable_3, R.mipmap.ic_credit_lable_3_grey},
            {R.mipmap.ic_credit_lable_4, R.mipmap.ic_credit_lable_4_grey},
            {R.mipmap.ic_credit_lable_5, R.mipmap.ic_credit_lable_5_grey}};
    private final String[] titles = {"身份", "活跃", "消费", "安全", "驾驶"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ftd", "onCreate");
        setContentView(R.layout.activity_test);
        Log.d("ftd", "setContentView");
        map_view = findViewById(R.id.map_view);
        init();


        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("name", "aac");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder = (MyService.MyBinder) service;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);

    }

    private void init() {
        ArrayList<AbilityBean> abilityBeans = new ArrayList<>();
        abilityBeans.add(new AbilityBean("活跃", 50f, 150f, R.mipmap.ic_credit_ability_1));
        abilityBeans.add(new AbilityBean("消费", 100f, 150f, R.mipmap.ic_credit_ability_2));
        abilityBeans.add(new AbilityBean("安全", 50f, 150f, R.mipmap.ic_credit_ability_3));
        abilityBeans.add(new AbilityBean("驾驶", 100f, 150f, R.mipmap.ic_credit_ability_4));
        abilityBeans.add(new AbilityBean("身份", 50f, 150f, R.mipmap.ic_credit_ability_5));
        map_view.setData(abilityBeans);

        map_view.setItems(icons, titles);
    }
}
