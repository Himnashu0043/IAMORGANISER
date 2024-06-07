package com.example.iamorganiser.util

import android.content.Context
import com.example.iamorganiser.modal.remote.LoginRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefManager private constructor(private val context: Context) {

    private val sharedPref = context.getSharedPreferences("DreamIASPref", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    var accessToken: String
        get() = sharedPref.getString("accessToken", "") ?: ""
        set(value) {
            editor.putString("accessToken", value).apply()
        }

    var deviceToken: String
        get() = sharedPref.getString("deviceToken", "") ?: ""
        set(value) {
            editor.putString("deviceToken", value).apply()
        }

    var isloggedIn: Boolean
        get() = sharedPref.getBoolean("isloggedIn", false)
        set(value) {
            editor.putBoolean("isloggedIn", value).apply()
        }
    var isTutorial: Boolean
        get() = sharedPref.getBoolean("isTutorial", false)
        set(value) {
            editor.putBoolean("isTutorial", value).apply()
        }
    var emailForget: String
        get() = sharedPref.getString("emailForget", "") ?: ""
        set(value) {
            editor.putString("emailForget", value).apply()
        }
    var otpSave: String
        get() = sharedPref.getString("otpSave", "") ?: ""
        set(value) {
            editor.putString("otpSave", value).apply()
        }
    var userId: String
        get() = sharedPref.getString("userId", "") ?: ""
        set(value) {
            editor.putString("userId", value).apply()
        }
    var questionId: String
        get() = sharedPref.getString("questionId", "") ?: ""
        set(value) {
            editor.putString("questionId", value).apply()
        }

    var loginData: LoginRes.Data?
        get() {
            val json = sharedPref.getString("loginData", null)
            val type = object : TypeToken<LoginRes.Data?>() {}.type
            return Gson().fromJson(json, type)
        }
        set(value) {
            val json = Gson().toJson(value)
            editor.putString("loginData", json).apply()
        }

   /* var userDetail: Data?
        get() {
            val json = sharedPref.getString("userDetail", null)
            val type = object : TypeToken<Data?>() {}.type
            return Gson().fromJson(json, type)
        }
        set(value) {
            val json = Gson().toJson(value)
            editor.putString("userDetail", json).apply()
        }*/

    fun clearPreferences() {
        editor.clear().apply()
    }

    companion object {
        fun get(context: Context) = PrefManager(context)
    }
}