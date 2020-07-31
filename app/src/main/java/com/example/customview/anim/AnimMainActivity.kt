package com.example.customview.anim

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R
import com.example.customview.anim.anim.Anim1Activity
import com.example.customview.anim.anim.Anim2Activity
import com.example.customview.anim.anim.Anim3Activity

class AnimMainActivity : AppCompatActivity() {

    val anim1 by lazy {
        findViewById<TextView>(R.id.anim_1)
    }
    val anim2 by lazy {
        findViewById<TextView>(R.id.anim_2)
    }
    val anim3 by lazy {
        findViewById<TextView>(R.id.anim_3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_main)
        anim1.apply {
            setOnClickListener {
                startActivity(Intent(context, Anim1Activity::class.java))
            }
        }
        anim2.apply {
            setOnClickListener {
                startActivity(Intent(context, Anim2Activity::class.java))
            }
        }
        anim3.apply {
            setOnClickListener {
                startActivity(Intent(context, Anim3Activity::class.java))
            }
        }
    }
}