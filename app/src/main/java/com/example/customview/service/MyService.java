package com.example.customview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MyBinder myBinder = new MyBinder();

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myBinder.time = System.currentTimeMillis();
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder {
        public long time;
    }
}
