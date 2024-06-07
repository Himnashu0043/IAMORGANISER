package com.example.iamorganiser.activity.updatePassword

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.activity.auth.LoginActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityUpdatePasswordBinding
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.local.ChanagePasswordPostModel
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePasswordBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        listener()


    }

    private fun initView() {
        observer()
    }

    private fun changePassword() {
        val model = ChanagePasswordPostModel(
            binding.etNewPassword.text?.trim().toString(),
            binding.etOldPassword.text?.trim().toString()
        )
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.changePasswordAPI(App.app.prefManager.accessToken, model)
        }
    }

    private fun observer() {
        viewModel.changePasswordLiveData.observe(this) {
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
                        finish()
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

    private fun listener() {
        binding.btnUpdate.setOnClickListener {
            if (binding.etOldPassword.text.toString().trim().isEmpty()) {
                shortToast("Please Enter Old Password")
            } else if (binding.etNewPassword.text.toString().trim().isEmpty()) {
                shortToast("PLease Enter New Password")
            } else if (binding.etConfirmPassword.text.toString().trim().isEmpty()) {
                shortToast("Please Enter Confirm Password")
            } else if (binding.etNewPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
                shortToast("Confirm Password is not Match")
            } else {
                changePassword()
            }
        }
        binding.inToolbar.imageView15.setOnClickListener {
            finish()
        }
    }
}