package com.example.customview.jetpack.wan.api

import com.example.customview.jetpack.wan.bean.ArticleBean
import com.example.customview.jetpack.wan.bean.WanBaseResponse
import com.facebook.litho.annotations.Param
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/1 0001
 *description:xxx
 *******************************
 */
interface WanApi {

    @GET("/article/list/{page}/json")
    fun getArticle(@Path("page") page: Int): WanBaseResponse<List<ArticleBean>>

}