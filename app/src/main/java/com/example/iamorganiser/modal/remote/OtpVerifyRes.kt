package com.example.iamorganiser.modal.remote

data class OtpVerifyRes(
    var code: Int?,
    var message: String?,
    var success: Boolean?
)