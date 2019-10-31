package com.example.customview.mvp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customview.R;

public class MvpActivity extends AppCompatActivity {

    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mvpPresenter = new MvpPresenter();
        getLifecycle().addObserver(mvpPresenter);
    }
}
