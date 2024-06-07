package com.example.iamorganiser.activity.setting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.activity.updatePassword.UpdatePasswordActivity
import com.example.iamorganiser.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        listener()


    }

    private fun initView() {

    }

    private fun listener() {
        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }
        binding.btnSave.setOnClickListener {
            finish()
        }
        binding.clPassword.setOnClickListener {
            startActivity(Intent(this, UpdatePasswordActivity::class.java))
        }
    }
}