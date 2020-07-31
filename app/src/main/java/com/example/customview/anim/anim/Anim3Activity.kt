package com.example.customview.anim.anim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.customview.R
import com.example.customview.ui.fragment.EmptyFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class Anim3Activity : AppCompatActivity() {
    val bottom_bar by lazy {
        findViewById<BottomNavigationView>(R.id.bottom_bar)
    }
    val f1 = EmptyFragment.newInstance("1")
    val f2 = EmptyFragment.newInstance("2")
    val f3 = EmptyFragment.newInstance("3")
    var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim3)

        bottom_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_1 -> {
                    switch(f1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_2 -> {
                    switch(f2)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_3 -> {
                    switch(f3)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        switch(f1)
    }

    private fun switch(fragment: Fragment) {
        if (fragment == currentFragment) return
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.apply {
            setCustomAnimations(R.animator.anim3_fragment_enter, R.animator.anim3_fragment_exit)
            if (fragment.isAdded) {
                show(fragment)
            } else {
                add(R.id.fl_layout, fragment, fragment.toString())
            }
            currentFragment?.let {
                hide(it)
            }
            commit()
        }
        currentFragment = fragment
    }

}