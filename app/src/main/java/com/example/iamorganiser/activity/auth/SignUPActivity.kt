package com.example.iamorganiser.activity.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.databinding.ActivitySignUpactivityBinding

class SignUPActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,EnterOtpActivity::class.java))
        }
    }
}