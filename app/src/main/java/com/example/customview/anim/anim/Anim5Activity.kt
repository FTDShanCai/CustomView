package com.example.customview.anim.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.transition.*
import com.example.customview.R

/**
 * 场景动画
 */
class Anim5Activity : AppCompatActivity() {

    val inView by lazy {
        findViewById<ViewGroup>(R.id.in_view)
    }

    val btn_1 by lazy {
        findViewById<Button>(R.id.btn_1)
    }

    val btn_2 by lazy {
        findViewById<Button>(R.id.btn_2)
    }

    val aScene by lazy {
        Scene.getSceneForLayout(inView, R.layout.in_animt5_a_scene, this)
    }

    val bScene by lazy {
        Scene.getSceneForLayout(inView, R.layout.in_animt5_b_scene, this)
    }

    var type = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim5)

        btn_1.setOnClickListener {
            if (type == 1)
                return@setOnClickListener
            var et1 = findViewById<EditText>(R.id.tv_start_city)
            var et2 = findViewById<EditText>(R.id.tv_end_city)
            val temp = listOf(et1.text, et2.text)
            TransitionManager.go(aScene)
            et1 = findViewById(R.id.tv_start_city)
            et2 = findViewById(R.id.tv_end_city)
            et1.text = temp[0]
            et2.text = temp[1]
            type = 1
        }

        btn_2.setOnClickListener {
            if (type == 2)
                return@setOnClickListener
            var et1 = findViewById<EditText>(R.id.tv_start_city)
            var et2 = findViewById<EditText>(R.id.tv_end_city)
            val temp = listOf(et1.text, et2.text)
            val t = AutoTransition()
            t.apply {
                duration=3000
            }
            TransitionManager.go(bScene,t)
            et1 = findViewById(R.id.tv_start_city)
            et2 = findViewById(R.id.tv_end_city)
            et1.text = temp[0]
            et2.text = temp[1]
            type = 2
        }
    }
}