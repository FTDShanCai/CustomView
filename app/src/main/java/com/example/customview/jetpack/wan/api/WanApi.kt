package com.example.customview.jetpack.wan.api

import com.example.customview.jetpack.wan.bean.ArticleBean
import com.example.customview.jetpack.wan.bean.WanBaseResponse
import com.facebook.litho.annotations.Param
import com.retrofit.rx2.cache.adapter.extend.Rx2Cache
import com.retrofit.rx2.cache.adapter.extend.Rx2CacheType
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
    @Rx2Cache(CacheType = Rx2CacheType.NO_CACHE)
    @GET("/article/list/{page}/json")
    fun getArticle(@Path("page") page: Int): Observable<WanBaseResponse<List<ArticleBean>>>
    @Rx2Cache(CacheType = Rx2CacheType.DB_OR_NET)
    @GET("/article/list/{page}/json")
    fun getArticle1(@Path("page") page: Int): Observable<WanBaseResponse<List<ArticleBean>>>

}