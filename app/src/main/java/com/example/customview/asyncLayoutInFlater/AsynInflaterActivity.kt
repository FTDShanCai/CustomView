package com.example.customview.asyncLayoutInFlater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.example.customview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.time.measureTime

class AsynInflaterActivity : AppCompatActivity() {
    val rootView: ViewGroup by lazy {
        findViewById<ViewGroup>(R.id.root_view)
    }
    val fab: FloatingActionButton by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyn_inflater)
        fab.setOnClickListener {
            addView()
        }

    }

    fun  addView(){
        val inflater = AsyncLayoutInflater(this)
        val start = System.currentTimeMillis()
        inflater.inflate(R.layout.view_stub_layout, rootView) { view, _, parent ->
            val end =  System.currentTimeMillis()
            Log.d("ftd","time:${end-start} ms")
            view.parent?.let {

            }?:run{
                parent?.addView(view)
            }
        }
    }
}