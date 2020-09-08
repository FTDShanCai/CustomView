package com.example.customview.hilt;

import com.example.customview.hilt.bean.Tools;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/9/8 0008
 * description:xxx
 * ******************************
 */
@EntryPoint
@InstallIn(ApplicationComponent.class)
public interface ProviderEntryPoint {
    Tools tools();
}