package com.example.iamorganiser.activity.cong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iamorganiser.MainActivity
import com.example.iamorganiser.databinding.ActivityCongratulationBinding
import com.example.iamorganiser.modal.remote.home.EntryHomeRes

class CongratulationActivity : AppCompatActivity() {
    lateinit var binding: ActivityCongratulationBinding
    private var from: String? = ""
    private var entry_list = ArrayList<EntryHomeRes.Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        from = intent.getStringExtra("from") ?: ""
        println("=======from$from")
        initView()
        lstnr()
    }

    private fun initView() {
        if (from == "list") {
            entry_list = intent.getSerializableExtra("entry_list") as ArrayList<EntryHomeRes.Data>
            if (entry_list.size <= 1) {
                binding.textView12.text = entry_list[0].name ?: ""
            } else {
                binding.textView12.text = entry_list[0].name ?: ""
                binding.tvOther.text = "& ${entry_list.size - 1} Other"

            }
        }

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