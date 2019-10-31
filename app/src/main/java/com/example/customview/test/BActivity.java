package com.example.customview.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.customview.R;
import com.example.customview.bus.LiveDataBus;

public class BActivity extends AppCompatActivity {
    ABCViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("ftd", "B onCreate");
        viewModel = ViewModelProviders.of(this).get(ABCViewModel.class);
        Log.d("ftd", "hashCode:" + viewModel.hashCode());
    }

    public void fenSan(View view) {
        LiveDataBus.get().getChannel("a", String.class).postValue("你好啊  from B");


        String str = "hello";
        change(str);
        System.out.println(str);

        A a = new A("hello");
        change(a);
        System.out.println(a.str);

        A a1 = new A("hello");
        change1(a1);
        System.out.println(a1.str);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("ftd", "B onNewIntent");
    }

    private static void change(String str) {
        str = "changed";
    }

    private static void change(A a) {
        a = new A("changed");
    }

    private static void change1(A a1) {
        a1.str = "changed";
    }

}
