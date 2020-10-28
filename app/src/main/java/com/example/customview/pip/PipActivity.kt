package com.example.customview.pip

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.customview.R
import kotlinx.android.synthetic.main.activity_pip.*

class PipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)
        fab_enter.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                if (hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE))
                enterPictureInPictureMode()
            }
        }

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Log.d("ftd", "event${event.name}");
            }
        })

    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            fab_enter.visibility = View.GONE
            tv.text = "画中画模式"
            root_view.setBackgroundColor(ContextCompat.getColor(this, R.color.zi))
        } else {
            fab_enter.visibility = View.VISIBLE
            tv.text = "正常"
            root_view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        }
    }


}