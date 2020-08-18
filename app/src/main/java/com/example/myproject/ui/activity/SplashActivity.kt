package com.example.myproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myproject.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            delay(3000)
            loadActivity()
        }


    }

    private fun loadActivity() {
        val intent = Intent(this, BlackActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

}
