package com.example.iamorganiser.activity.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityEnterOtpBinding
import com.example.iamorganiser.helper.CommonUtil
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.local.OtpVerifyPostModel
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterOtpBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        listener()

    }

    private val currentCode
        get() = binding.etOne.text.trim().toString() + binding.etTwo.text.trim()
            .toString() + binding.etThree.text.trim().toString() + binding.etFour.text.trim()
            .toString() + binding.etfive.text.trim()
            .toString() + binding.etsix.text.trim()
            .toString()

    private fun initView() {
        binding.tvDescription.text =
            "OTP has been sent to your Email address ${App.app.prefManager.otpSave}"
        observer()
        binding.etOne.addTextChangedListener(textChangeListener)
        binding.etTwo.addTextChangedListener(textChangeListener)
        binding.etThree.addTextChangedListener(textChangeListener)
        binding.etFour.addTextChangedListener(textChangeListener)
        binding.etfive.addTextChangedListener(textChangeListener)
        binding.etsix.addTextChangedListener(textChangeListener)
        binding.etOne.setOnKeyListener(backKeyListener)
        binding.etTwo.setOnKeyListener(backKeyListener)
        binding.etThree.setOnKeyListener(backKeyListener)
        binding.etFour.setOnKeyListener(backKeyListener)
        binding.etfive.setOnKeyListener(backKeyListener)
        binding.etsix.setOnKeyListener(backKeyListener)
        binding.tvEmail1.text = App.app.prefManager.emailForget
    }

    private var backClicked = false
    private val backKeyListener = View.OnKeyListener { v, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            Log.d("TAG", "back clicked")
            backClicked = true
            true
        } else backClicked = false
        false
    }
    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if (!backClicked) {
                when (currentCode.length) {
                    0 -> binding.etOne.requestFocus()
                    1 -> binding.etTwo.requestFocus()
                    2 -> binding.etThree.requestFocus()
                    3 -> binding.etFour.requestFocus()
                    4 -> binding.etfive.requestFocus()
                    5 -> binding.etsix.requestFocus()
                }
            } else {
                Log.d("TAG", "afterTextChanged: ${currentCode.length}")
                when (currentCode.length) {
                    0 -> binding.etOne.requestFocus()
                    1 -> binding.etOne.requestFocus()
                    2 -> binding.etTwo.requestFocus()
                    3 -> binding.etThree.requestFocus()
                    4 -> binding.etFour.requestFocus()
                    5 -> binding.etfive.requestFocus()
                }
            }
        }
    }

    private fun listener() {
        binding.enterMain.setOnClickListener {
            CommonUtil.hideKeyBoard(this)
        }
        binding.tvEdit.setOnClickListener {
            startActivity(
                Intent(this, ForgotPassword::class.java).putExtra("from", "edit")
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }

        binding.btnSubmitOTP.setOnClickListener {
            println("--------$currentCode")
            if (currentCode.isEmpty()) {
                shortToast("Please Enter Otp!!")
                return@setOnClickListener
            } else if (currentCode.length < 6) {
                shortToast("Please Enter Valid Otp!!")
                return@setOnClickListener
            }
            otp()
            /*startActivity(Intent(this, ResetPasswordActivity::class.java))
            finish()*/
        }
    }

    private fun otp() {
        val model = OtpVerifyPostModel(App.app.prefManager.emailForget, currentCode.toInt())
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.otpVerifyAPI(model)
        }
    }

    private fun observer() {
        viewModel.otpVerifyLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    if (it.value?.code == Constants.SUCCESS) {
                        Loaders.hide()
                        startActivity(Intent(this, ResetPasswordActivity::class.java))
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