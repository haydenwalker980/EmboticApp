package com.embotic.nodes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // Start a coroutine to simulate a long-running task
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)  // Simulate a long-running task

            // Navigate to MainActivity after the task is done
            val intent = Intent(this@LoadingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}