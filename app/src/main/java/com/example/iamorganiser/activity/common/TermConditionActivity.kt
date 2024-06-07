package com.example.iamorganiser.activity.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.databinding.ActivityTermConditionBinding

class TermConditionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTermConditionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermConditionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }
    }
}