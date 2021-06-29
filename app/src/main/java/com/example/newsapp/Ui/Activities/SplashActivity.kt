package com.example.newsapp.Ui.Activities

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private val splashInterval = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val cManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cManager.activeNetworkInfo
            if (nInfo != null && nInfo.isConnected) {
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                this.finish()
            } else {
                Toast.makeText(
                    this@SplashActivity,
                    "بعد إذنك افحص شبكة الأنترنت",
                    Toast.LENGTH_SHORT
                ).show()
                this.finish()
            }
        }, splashInterval.toLong())


    }
}
