package com.example.customview.jetpack.wan.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
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
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(GoodsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wan_main)

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
}