package com.example.iamorganiser.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.R
import com.example.iamorganiser.activity.auth.LoginActivity
import com.example.iamorganiser.activity.onBoarding.OnBoardingActivity
import com.example.iamorganiser.databinding.ActivitySplashBinding
import com.example.iamorganiser.helper.PrefrencesHelper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var isTutorial: Boolean = true
    private lateinit var binding: ActivitySplashBinding
    private val splashDuration = 3000 // 3 seconds (adjust as needed)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isTutorial = PrefrencesHelper.getTutorialStatus(this)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        intView()
        lstnr()
        /* Handler().postDelayed({
             val intent = Intent(this, LoginActivity::class.java)
             startActivity(intent)
             finish()
         }, splashDuration.toLong())*/
    }

    private fun intView() {
        val path =
            "android.resource://" + packageName + "/" + R.raw.splash_video
        binding.VideoView.setVideoURI(Uri.parse(path))
        binding.VideoView.setOnCompletionListener {
            if (isTutorial) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        binding.VideoView.start()
    }

    private fun lstnr() {


    }
}