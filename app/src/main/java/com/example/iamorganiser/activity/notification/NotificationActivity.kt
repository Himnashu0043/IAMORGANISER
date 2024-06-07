package com.example.iamorganiser.activity.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iamorganiser.R
import com.example.iamorganiser.adapter.NotificationAdapter
import com.example.iamorganiser.databinding.ActivityNotificationBinding
import com.example.iamorganiser.modal.NotiModel

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    private var adptr: NotificationAdapter? = null
    private var list = ArrayList<NotiModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        lstnr()
    }

    private fun initView() {
        binding.toolbar.textView38.text = "Notifications"

        list.add(NotiModel(R.drawable.google_icon, "25 minutes ago"))
        list.add(NotiModel(R.drawable.digg_icon, "45 minutes ago"))
        list.add(NotiModel(R.drawable.twitter_icon, "5 Hours ago"))
        list.add(NotiModel(R.drawable.digg_icon, "1 Day ago"))
        list.add(NotiModel(R.drawable.facebook_icon, "12 February 2022"))
        binding.rcy.layoutManager = LinearLayoutManager(this)
        adptr = NotificationAdapter(this, list)
        binding.rcy.adapter = adptr
        adptr!!.notifyDataSetChanged()

    }

    private fun lstnr() {
        binding.toolbar.imageView15.setOnClickListener {
            finish()
        }


    }
}