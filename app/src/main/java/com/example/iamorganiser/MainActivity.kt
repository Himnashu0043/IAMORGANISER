package com.example.iamorganiser

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.iamorganiser.activity.notification.NotificationActivity
import com.example.iamorganiser.activity.profile.ProfileActivity
import com.example.iamorganiser.databinding.ActivityMainBinding
import com.example.iamorganiser.fragment.HomeFragment
import com.example.iamorganiser.fragment.ScanFragment
import com.example.iamorganiser.util.App

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var doubleBackToExitPressedOnce = false
    val fragHome = HomeFragment()

    val fragScan = ScanFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println("==========${App.app.prefManager.accessToken}")
        initView()
        lstnr()
        initCtrl()

    }

    private fun initCtrl() {
        binding.ivHome.setOnClickListener(this)
        binding.ivScan.setOnClickListener(this)


    }

    private fun initView() {
        if (!App.app.prefManager.loginData?.profileImage.isNullOrEmpty()){
            Glide.with(this).load(App.app.prefManager.loginData?.profileImage.toString())
                .into(binding.img)
        }
        binding.tvtittleTrans.text = "${App.app.prefManager.loginData?.firstName ?: ""}${
            App.app.prefManager.loginData?.lastName ?: ""
        }"
        binding.tvdescTrans.text = App.app.prefManager.loginData?.address ?: ""

        onSelectView("Home")

    }

    private fun lstnr() {
        binding.img.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.notiIcon.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_home -> {
                onSelectView("Home")
            }

            R.id.ivScan -> {
                onSelectView("Scan")
            }


        }
    }

    private fun onSelectView(from: String) {
        if (from == "Home") {
            binding.ivHome.setImageDrawable(resources.getDrawable(R.drawable.home_icon_active))
            binding.ivScan.setImageDrawable(resources.getDrawable(R.drawable.scan_icon_unactive))
            replaceFrag(fragHome, "fragHome", null)

        } else {
            binding.ivHome.setImageDrawable(resources.getDrawable(R.drawable.home_unactive))
            binding.ivScan.setImageDrawable(resources.getDrawable(R.drawable.scan_active))
            replaceFrag(fragScan, "fragScan", null)
        }

    }

    private fun replaceFrag(frag: Fragment, nameTag: String, bundle: Bundle?) {
        if (bundle != null) frag.setArguments(bundle) else frag.setArguments(null)
        supportFragmentManager.beginTransaction().replace(R.id.frame, frag, nameTag)
            .addToBackStack(nameTag).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (doubleBackToExitPressedOnce) {
            super.finishAffinity()
            return
        }
        if (supportFragmentManager.findFragmentByTag("fragHome") != null) {
            onSelectView("Home")
        }
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = true
        }, 1000)
    }
}