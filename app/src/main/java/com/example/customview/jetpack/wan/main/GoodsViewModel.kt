package com.example.customview.jetpack.wan.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customview.jetpack.wan.bean.Goods

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/2 0002
 *description:xxx
 *******************************
 */
class GoodsViewModel : ViewModel() {

    val goods = MutableLiveData<Goods>()

    init {
        val good = Goods()
        good.count = 0
        good.goodsName = "apple"
        goods.postValue(good)
    }

    fun addCount() {
        val good = goods.value
        good?.let {
            it.count++
        }
        goods.postValue(good)
    }

    fun setGoodsName(name: String?) {
        val good = goods.value
        good?.let {
            it.goodsName = name
        }
        goods.postValue(good)
    }

}