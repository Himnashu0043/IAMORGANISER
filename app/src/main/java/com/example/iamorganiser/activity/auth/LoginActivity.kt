package com.example.iamorganiser.activity.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.MainActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityLoginBinding
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


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        listener()


    }

    private fun initView() {
        observer()
    }

    private fun login() {
        val model = ResetPasswordPostModel(
            binding.etEmail.text?.trim().toString(),
            binding.etPassword.text?.trim().toString()
        )
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.loginAPI(model)
        }
    }

    private fun observer() {
        viewModel.loginLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        App.app.prefManager.accessToken = it.value.data?.jwtToken.toString()
                        App.app.prefManager.loginData = it.value.data
                        App.app.prefManager.isloggedIn = true
                        startActivity(Intent(this, MainActivity::class.java))
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

    private fun listener() {
        binding.main.setOnClickListener {
            if (this.currentFocus == null) {
                return@setOnClickListener
            }
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUPActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            if (binding.etEmail.text.toString().trim().isEmpty()) {
                shortToast("Please Enter Email")
            } else if (binding.etPassword.text.toString().trim().isEmpty()) {
                shortToast("PLease Enter Password")
            } else {
                login()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (App.app.prefManager.isloggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}