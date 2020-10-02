package com.example.customview.jetpack.wan.base

import androidx.lifecycle.ViewModel
import com.example.customview.jetpack.wan.api.ApiFactory
import com.example.customview.jetpack.wan.api.WanApi

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/1 0001
 *description:xxx
 *******************************
 */
open class BaseViewModel :ViewModel() {

    val api  by lazy {
        ApiFactory.instance.create(WanApi::class.java)
    }


}