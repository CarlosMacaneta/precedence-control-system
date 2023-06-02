package com.cm.precedencecontrolsystem.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cm.precedencecontrolsystem.databinding.ActivitySplashBinding
import com.cm.precedencecontrolsystem.ui.login.LoginActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainScope().launch {
            delay(2000L)

            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}