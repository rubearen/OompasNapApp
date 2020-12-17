package com.rubenarenas.oompasnap.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rubenarenas.oompasnap.R

class OompaMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.conslayout,
                OompaListFragment()
            ).addToBackStack(null).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
            finish()
        }
    }
}

