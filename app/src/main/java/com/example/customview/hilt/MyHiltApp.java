package com.example.customview.hilt;

import android.app.Application;
import android.util.Log;

import com.facebook.soloader.SoLoader;

import dagger.hilt.android.HiltAndroidApp;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/9/8 0008
 * description:xxx
 * ******************************
 */
@HiltAndroidApp
public class MyHiltApp extends Application {
    public static Application APP;
    @Override
    public void onCreate() {
        super.onCreate();
        APP=this;
        SoLoader.init(this, false);
        Log.d("ftd", "Application  onCreate");
    }
}
