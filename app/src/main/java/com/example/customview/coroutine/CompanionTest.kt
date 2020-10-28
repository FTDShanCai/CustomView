package com.example.customview.coroutine

import kotlin.coroutines.CoroutineContext

/**
 *******************************
 *@Author  YOULU-ddc
 *date ：2020/10/13 0013
 *description:xxx
 *******************************
 */
public interface CompanionTest : CoroutineContext.Element {


    companion object : CoroutineContext.Key<CompanionTest> {

    }

}