package com.example.customview.jetpack.wan.main

import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customview.jetpack.wan.api.ApiFactory
import com.example.customview.jetpack.wan.api.WanApi
import com.example.customview.jetpack.wan.base.BaseViewModel
import com.example.customview.jetpack.wan.bean.ArticleBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/1 0001
 *description:xxx
 *******************************
 */
class WanViewModel : BaseViewModel() {
    val datas: MutableLiveData<List<ArticleBean>> = MutableLiveData()

    fun getArticle(pager: Int) {
        viewModelScope.launch {
            val withContext = withContext(Dispatchers.IO) {
                api.getArticle(pager)
            }
        }
    }


    fun map(){
        val size = Transformations.map(datas) {
            it.size
        }
    }

}