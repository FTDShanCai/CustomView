package com.example.customview;

import android.app.Application;
import android.util.Log;

import com.facebook.soloader.SoLoader;

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
        Log.d("ftd", "Application  onCreate");
    }
}