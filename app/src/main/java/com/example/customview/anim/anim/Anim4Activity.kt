package com.example.customview.anim.anim

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.example.customview.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.hypot

class Anim4Activity : AppCompatActivity() {
    val ivPic by lazy {
        findViewById<ImageView>(R.id.iv_pic)
    }
    val btnShow by lazy {
        findViewById<Button>(R.id.btn_show)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim4)

        ivPic.visibility = View.INVISIBLE

        btnShow.setOnClickListener {
            val x = ivPic.width / 2
            val y = ivPic.height / 2

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val finalRadius = hypot(x.toDouble(), y.toDouble()).toFloat()
                val anim = ViewAnimationUtils.createCircularReveal(
                        ivPic, x, y, 0f, finalRadius
                )
                ivPic.visibility = View.VISIBLE
                anim.duration = 2000
                anim.start()
            }
        }

    }
}