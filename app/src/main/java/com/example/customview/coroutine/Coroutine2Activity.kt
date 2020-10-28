package com.example.customview.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Coroutine2Activity : AppCompatActivity() {

    val fab by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine2)

        fab.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }
    }


}