package com.example.iamorganiser.activity.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityResetPasswordBinding
import com.example.iamorganiser.helper.CommonUtil
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.local.ResetPasswordPostModel
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intView()
        listener()


    }

    private fun intView() {
        observer()
    }

    private fun listener() {
        binding.resetMain.setOnClickListener {
            CommonUtil.hideKeyBoard(this)
        }
        binding.btnLogin.setOnClickListener {
            if (binding.etPassword.text.toString().trim().isEmpty()) {
                shortToast("Please Enter New Password!!")
            } else if (binding.etConfirmPassword.text.toString().trim().isEmpty()) {
                shortToast("PLease Enter Confirm Password!!")
            } else if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
                shortToast("Confirm Password is not Match!!")
            } else {
                resetPassword()
            }
        }
    }

    private fun resetPassword() {
        val model = ResetPasswordPostModel(
            App.app.prefManager.emailForget,
            binding.etPassword.text?.trim().toString()
        )
        viewModel.resetPasswordAPI(model)
        /*CoroutineScope(Dispatchers.Main).launch {

        }*/
    }

    private fun observer() {
        viewModel.resetPasswordLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        startActivity(Intent(this, CongoActivity::class.java))
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
}