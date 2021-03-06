package com.setiaki.moviecatalogueexpert.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.setiaki.moviecatalogueexpert.databinding.ActivitySplashBinding
import com.setiaki.moviecatalogueexpert.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val toHomeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(toHomeIntent)
            finish()
        }, 3000)
    }
}