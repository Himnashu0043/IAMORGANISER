package com.example.iamorganiser.modal.remote

data class ForgetPasswordRes(
    var code: Int?,
    var data: Data?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var otp: Int?
    )
}