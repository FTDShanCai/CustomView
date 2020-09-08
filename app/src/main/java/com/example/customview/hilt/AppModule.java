package com.example.customview.hilt;

import com.example.customview.hilt.bean.Hammer;
import com.example.customview.hilt.bean.Tools;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.scopes.ActivityScoped;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/9/8 0008
 * description:xxx
 * ******************************
 */
@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Tools providersTools() {
        return new Hammer();
    }
}
