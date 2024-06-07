package com.example.iamorganiser.activity.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iamorganiser.adapter.HelpSupportAdapter
import com.example.iamorganiser.databinding.ActivityHelpSupportBinding

class HelpSupportActivity : AppCompatActivity() {
    private lateinit var adapter: HelpSupportAdapter
    private lateinit var binding : ActivityHelpSupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpSupportBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()
    }

    private fun init(){
        binding.inToolbar.textView38.text = "Help and Support"
        binding.rvHelpSupport.layoutManager = LinearLayoutManager(this)
        adapter = HelpSupportAdapter(this,
            emptyList<String>()
        )
        binding.rvHelpSupport.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }
        binding.inContactInformation.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+1012 3456 789")
            startActivity(intent)
        }


    }

}