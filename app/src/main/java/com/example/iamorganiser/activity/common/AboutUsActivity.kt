package com.example.iamorganiser.activity.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }

    }
}