package com.example.customview.anim.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * 属性动画、组合动画
 */
class Anim2Activity : AppCompatActivity() {
    val fab by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    val btnStart by lazy {
        findViewById<Button>(R.id.btn_start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim2)

        btnStart.setOnClickListener {
            val animX = ObjectAnimator.ofFloat(fab, "translationX", fab.translationX, fab.translationX + 100f)
                    .apply {
                        duration = 1000
                    }

            val animY = ObjectAnimator.ofFloat(fab, "translationY", fab.translationY, fab.translationY + 100f)
                    .apply {
                        duration = 1000
                    }

            AnimatorSet().apply {
                play(animX).with(animY)
                start()
            }
        }
    }


}