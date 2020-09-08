package com.example.customview.hilt;

import android.util.Log;

import com.example.customview.hilt.bean.Tools;

import dagger.hilt.android.EntryPointAccessors;

import static com.example.customview.hilt.MyHiltApp.APP;

/**
 * ******************************
 *
 * @Author YOULU-ddc
 * date ：2020/9/8 0008
 * description:xxx
 * ******************************
 */
public class MyPresenter {

    public void init() {
        ProviderEntryPoint entryPoint = EntryPointAccessors.fromApplication(APP, ProviderEntryPoint.class);
        Tools tools = entryPoint.tools();
        Log.d("ftd", "tools : " + tools.getName() + " hasCode:" + tools.hashCode());
    }

}
