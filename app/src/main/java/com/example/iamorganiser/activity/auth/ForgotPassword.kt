package com.example.iamorganiser.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityForgotPasswordBinding
import com.example.iamorganiser.helper.CommonUtil
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.local.ForgetPasswordPostModel
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private var from: String = ""
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        from = intent.getStringExtra("from") ?: ""
        if (from == "edit") {
            binding.etEmail.setText(App.app.prefManager.emailForget)
        }
        initView()
        listener()


    }

    private fun forgetPassword() {
        App.app.prefManager.emailForget = binding.etEmail.text?.trim().toString()
        val model = ForgetPasswordPostModel(binding.etEmail.text?.trim().toString())
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.forgetPasswordAPI(model)
        }
    }

    private fun observer() {
        viewModel.forgetPasswordLiveData.observe(this@ForgotPassword) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    if (it.value?.code == Constants.SUCCESS) {
                        Loaders.hide()
                        App.app.prefManager.otpSave = (it.value.data?.otp ?: 0).toString()
                        startActivity(
                            Intent(this, EnterOtpActivity::class.java)
                        )
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

    private fun initView() {
        observer()
    }

    private fun listener() {
        binding.forMain.setOnClickListener {
            CommonUtil.hideKeyBoard(this@ForgotPassword)
        }

        binding.btnLogin.setOnClickListener {
            if (!validationData()) {
                return@setOnClickListener
            }
            forgetPassword()

        }
        binding.btnBackLogin.setOnClickListener {
            finish()
        }
    }

    private fun validationData(): Boolean {
        if (TextUtils.isEmpty(binding.etEmail.text!!.trim().toString())) {
            Toast.makeText(
                applicationContext, "Please Enter Email!!", Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true

    }
}