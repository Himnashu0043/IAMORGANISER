package com.example.iamorganiser.activity.cong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iamorganiser.MainActivity
import com.example.iamorganiser.databinding.ActivityCongratulationNotiBinding

class CongratulationNotiActivity : AppCompatActivity() {
    lateinit var binding: ActivityCongratulationNotiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationNotiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        lstnr()
    }

    private fun initView() {

    }

    private fun lstnr() {
        binding.btnLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            finish()
        }

    }
}