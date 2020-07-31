package com.example.customview.anim.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
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
    val btnIntercept by lazy {
        findViewById<Button>(R.id.btn_intercept)
    }

    val btnReset by lazy {
        findViewById<Button>(R.id.btn_reset)
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

        btnIntercept.setOnClickListener {
            val path = Path().apply {
                moveTo(fab.x, fab.y)
                quadTo(300f, 100f, 500f, 500f)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val anim = ObjectAnimator.ofFloat(fab, View.X, View.Y, path).apply {
                    duration = 1000
                    start()
                }

            }
        }
        btnReset.setOnClickListener {
            fab.translationX = 0f
            fab.translationY = 0f
        }
    }


}