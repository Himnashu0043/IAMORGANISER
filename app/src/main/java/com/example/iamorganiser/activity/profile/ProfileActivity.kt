package com.example.iamorganiser.activity.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.iamorganiser.R
import com.example.iamorganiser.activity.auth.LoginActivity
import com.example.iamorganiser.activity.common.AboutUsActivity
import com.example.iamorganiser.activity.common.HelpSupportActivity
import com.example.iamorganiser.activity.common.PrivacyActivity
import com.example.iamorganiser.activity.common.TermConditionActivity
import com.example.iamorganiser.activity.setting.SettingsActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityProfileBinding
import com.example.iamorganiser.databinding.LogoutBottomSheetBinding
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var logOut_by_bottom: LogoutBottomSheetBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        lstner()
    }

    private fun initView() {
        observer()
        if (!App.app.prefManager.loginData?.profileImage.isNullOrEmpty()) {
            Glide.with(this).load(App.app.prefManager.loginData?.profileImage.toString())
                .into(binding.imgProfile)
        }
        binding.tvtittleTrans.text = "${App.app.prefManager.loginData?.firstName ?: ""}${
            App.app.prefManager.loginData?.lastName ?: ""
        }"
        binding.tvdescTrans.text = App.app.prefManager.loginData?.address ?: ""
    }

    private fun observer() {
        viewModel.logoutLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        App.app.prefManager.clearPreferences()
                        App.app.prefManager.isTutorial = true
                        App.app.prefManager.isloggedIn = false
                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )
                        finishAffinity()
                    } else if (it.value?.code == Constants.STATUS_404) {
                        shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    shortToast("Something Went Wrong")
                }
            }
        }
    }

    private fun lstner() {
        binding.cardView3.setOnClickListener {
            flat_BottomSheet()
        }
        binding.cardView.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.cardView2.setOnClickListener {
            startActivity(Intent(this, HelpSupportActivity::class.java))
        }
        binding.textView6.setOnClickListener {
            startActivity(Intent(this, PrivacyActivity::class.java))
        }
        binding.textView7.setOnClickListener {
            startActivity(Intent(this, TermConditionActivity::class.java))
        }
        binding.textView8.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

    }

    private fun flat_BottomSheet() {
        logOut_by_bottom = LogoutBottomSheetBinding.inflate(LayoutInflater.from(this))
        bottomSheetDialog =
            BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme).apply {
                setContentView(logOut_by_bottom.root)
                logOut_by_bottom.btnLogin.setOnClickListener {
                    bottomSheetDialog.dismiss()
                    viewModel._logout(App.app.prefManager.accessToken)
                }
                logOut_by_bottom.tvCancel.setOnClickListener {
                    bottomSheetDialog.dismiss()
                }

            }
        bottomSheetDialog.show()
    }
}