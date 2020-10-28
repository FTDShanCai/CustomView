package com.example.customview.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/13 0013
 *description:xxx
 *******************************
 */
class MyCoroutineInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = TODO("Not yet implemented")

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }
}