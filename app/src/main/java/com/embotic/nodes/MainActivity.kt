package com.embotic.nodes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
