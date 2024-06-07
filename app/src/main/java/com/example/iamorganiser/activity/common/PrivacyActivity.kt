package com.example.iamorganiser.activity.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.databinding.ActivityPrivacyBinding

class PrivacyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }
    }
}