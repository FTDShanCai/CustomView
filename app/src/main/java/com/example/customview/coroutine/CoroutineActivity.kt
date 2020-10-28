package com.example.customview.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.customview.R
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 *  协程
 */
class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
//        test0()
//        test1()
//        test2()
        test3()
    }

    fun test0() {
        GlobalScope.launch {
            Log.d("ftd", "GlobalScope launch:")
            lifecycleScope.launch {
                coroutineContext[CompanionTest]
                delay(2000)
                kotlin.runCatching {
                }.onSuccess {
                    Log.d("ftd", "launch onSuccess:" + Thread.currentThread().name)
                }.onFailure {
                    Log.d("ftd", "launch onFailure:" + Thread.currentThread().name)
                }
            }
        }

        Log.d("ftd", "" + Thread.currentThread().name)
    }

    fun test1() {
        runBlocking {
            GlobalScope.launch { // 在后台启动一个新的协程并继续
                delay(1000L)
                Log.d("ftd", "World!")
            }
            Log.d("ftd", "Hello,") // 主线程中的代码会立即执行
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }

    fun test2() {
        runBlocking {

            launch {
                delay(1500)
                Log.d("ftd", "launch  ${Thread.currentThread().name}")
            }
            coroutineScope {
                delay(1000)
                Log.d("ftd", "coroutineScope  ${Thread.currentThread().name}")
            }

            Log.d("ftd", "runBlocking  ${Thread.currentThread().name}")
        }
    }

    fun test3() {
        runBlocking {
            GlobalScope.launch {
                repeat(1000) { i ->
                    Log.d("ftd", "I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // 在延迟后退出
        }

        GlobalScope.launch {
            val s = getUser()
            print(s)
        }

    }


    suspend fun getUser() = suspendCoroutine<String> {
        it.resume("")
    }

}