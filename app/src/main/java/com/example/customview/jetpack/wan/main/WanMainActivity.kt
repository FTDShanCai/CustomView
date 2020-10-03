package com.example.customview.jetpack.wan.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.customview.R

class WanMainActivity : AppCompatActivity() {

    val tvCount: TextView by lazy {
        findViewById<TextView>(R.id.tv_count)
    }
    val tvName: TextView by lazy {
        findViewById<TextView>(R.id.tv_name)
    }
    val btnAdd: Button by lazy {
        findViewById<Button>(R.id.btn_add)
    }
    val btnSubmit: Button by lazy {
        findViewById<Button>(R.id.btn_submit)
    }
    val etInput: EditText by lazy {
        findViewById<EditText>(R.id.et_input)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)
                .get(GoodsViewModel::class.java)
    }

    private val viewModel2 by lazy {
        ViewModelProvider(this)
                .get(GoodsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wan_main)

//        goodSet()
        mediatorSet()

        Log.d("ftd", "hasCode:${viewModel.hashCode()}       :${viewModel2.hashCode()}")
    }

    private fun goodSet() {
        Transformations.map(viewModel.goods) {
            it.count
        }.observe(this, Observer {
            Log.d("ftd", "count changed:$it")
            tvCount.text = it.toString()
        })

        Transformations.map(viewModel.goods) {
            it.goodsName
        }.observe(this, Observer {
            Log.d("ftd", "name changed:$it")
            tvName.text = it.toString()
        })

        btnAdd.setOnClickListener {
            viewModel.addCount()
        }

        btnSubmit.setOnClickListener {
            val input = etInput.text.toString()
            viewModel.setGoodsName(input)
        }
    }


    val count = MutableLiveData<Long>()

    val name = MutableLiveData<String>()

    val mediator = MediatorLiveData<String>()

    private fun mediatorSet() {

        mediator.addSource(count) {
            Log.d("ftd", "count changed:$it")
            tvCount.text = it.toString()
        }
        mediator.addSource(name) {
            Log.d("ftd", "name changed:$it")
            tvName.text = it.toString()
        }
        mediator.observe(this, Observer {
            Log.d("ftd", "mediator changed:$it")
        })

        btnAdd.setOnClickListener {
            count.postValue(System.currentTimeMillis())
        }

        btnSubmit.setOnClickListener {
            val input = etInput.text.toString()
            name.postValue(input)
        }
    }

}