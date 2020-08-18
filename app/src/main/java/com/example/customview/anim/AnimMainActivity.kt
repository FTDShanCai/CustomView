package com.example.customview.anim

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R
import com.example.customview.anim.anim.*
import com.example.customview.ui.SharedElement1Activity

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
    val anim4 by lazy {
        findViewById<TextView>(R.id.anim_4)
    }
    val anim5 by lazy {
        findViewById<TextView>(R.id.anim_5)
    }
    val anim6 by lazy {
        findViewById<TextView>(R.id.anim_6)
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
        anim4.apply {
            setOnClickListener {
                startActivity(Intent(context, Anim4Activity::class.java))
            }
        }
        anim5.apply {
            setOnClickListener {
                startActivity(Intent(context, Anim5Activity::class.java))
            }
        }
        anim6.apply {
            setOnClickListener {
                startActivity(Intent(context, SharedElement1Activity::class.java))
            }
        }
    }
}