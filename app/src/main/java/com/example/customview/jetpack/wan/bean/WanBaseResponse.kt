package com.example.customview.jetpack.wan.bean

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/1 0001
 *description:xxx
 *******************************
 */
data class WanBaseResponse<T>(val datas: T, val errorCode: Int, val errorMsg: String)