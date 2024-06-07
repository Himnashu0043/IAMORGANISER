package com.example.iamorganiser.helper

import android.view.View
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtil {
    fun handlerGeneralError(view: View?, throwable: Throwable) {
        throwable.printStackTrace()
        if (view == null) return
        when (throwable) {
            is ConnectException -> snackView(view, "Please turn on Internet")
            is SocketTimeoutException -> snackView(view, "Server is not responding. Please try again!")
            is UnknownHostException -> snackView(view, "No Internet Connection.Please check your internet connection!")
            is InternalError -> snackView(view, "Internal Server Error")
            is HttpException -> {
                snackView(view, "Something went wrong")
            }
            else -> {
                snackView(view, "Something went wrong")
            }
        }
    }

    fun snackView(view: View, message: String) {
        try {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        } catch (e: Exception) {
            println(e.printStackTrace())
        }
    }
}


