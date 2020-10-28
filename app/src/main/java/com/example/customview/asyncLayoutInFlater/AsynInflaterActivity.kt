package com.example.customview.asyncLayoutInFlater

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.example.customview.R
import com.facebook.soloader.FileLocker.lock
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

//        with(MM()) {
//            object : MM.MMM() {
//
//            }
//        }

        Observable.just(1)
                .map {
                    it.toString()
                }
                .flatMap { Observable.just("a") }
                .subscribe()

    }

    fun addView() {
        val inflater = AsyncLayoutInflater(this)
        val start = System.currentTimeMillis()
        inflater.inflate(R.layout.view_stub_layout, rootView) { view, _, parent ->
            val end = System.currentTimeMillis()
            Log.d("ftd", "time:${end - start} ms")
            view.parent?.let {

            } ?: run {
                parent?.addView(view)
            }
        }
    }
}