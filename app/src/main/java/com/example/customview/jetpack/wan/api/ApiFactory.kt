package com.example.customview.jetpack.wan.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/1 0001
 *description:xxx
 *******************************
 */
class ApiFactory {

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    companion object {
        val instance by lazy {
            ApiFactory()
        }
    }
}