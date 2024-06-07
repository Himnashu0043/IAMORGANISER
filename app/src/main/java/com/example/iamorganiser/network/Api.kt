package com.example.iamorganiser.network

import com.example.iamorganiser.adapter.HomeAdapter
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.modal.local.ChanagePasswordPostModel
import com.example.iamorganiser.modal.local.ForgetPasswordPostModel
import com.example.iamorganiser.modal.local.OtpVerifyPostModel
import com.example.iamorganiser.modal.local.ResetPasswordPostModel
import com.example.iamorganiser.modal.remote.ForgetPasswordRes
import com.example.iamorganiser.modal.remote.LoginRes
import com.example.iamorganiser.modal.remote.NotificationRes
import com.example.iamorganiser.modal.remote.OtpVerifyRes
import com.example.iamorganiser.modal.remote.home.*
import com.example.iamorganiser.modal.remote.scan.ScanEventListRes
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @POST(Constants.FORGET_PASSWORD)
    suspend fun forgetPassword(@Body data: ForgetPasswordPostModel): ForgetPasswordRes?

    @POST(Constants.OTP_VERIFY)
    suspend fun otpVerify(@Body data: OtpVerifyPostModel): OtpVerifyRes?

    @POST(Constants.RESET_PASSWORD)
    suspend fun resetPassword(@Body data: ResetPasswordPostModel): OtpVerifyRes?

    @POST(Constants.LOGIN)
    suspend fun login(@Body data: ResetPasswordPostModel): LoginRes?

    @POST(Constants.CHANGE_PASSWORD)
    suspend fun changePassword(
        @Header("Authorization") auth: String,
        @Body data: ChanagePasswordPostModel
    ): OtpVerifyRes?

    @POST(Constants.LOGOUT)
    suspend fun logoutAPI(
        @Header("Authorization") auth: String
    ): OtpVerifyRes?

    @GET(Constants.EVENTS)
    suspend fun eventHomeAPI(
        @Header("Authorization") auth: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("eventCategory") eventCategory: String,
    ): HomeEventRes?

    @GET(Constants.EVENT_SLOT_DATE)
    suspend fun eventDateHomeAPI(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ): HomeDateRes

    @POST(Constants.EVENT_SLOT_TIME)
    @FormUrlEncoded
    suspend fun eventTimeHomeAPI(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Field("date") date: String
    ): HomeTimeRes

    @POST(Constants.IDEVENTS)
    @FormUrlEncoded
    suspend fun scanAPI(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
        @Field("slotId") slotId: String,
        @Field("date") date: String
    ): ScanlistRes?

    @PATCH(Constants.NOTIFICATION)
    @FormUrlEncoded
    suspend fun notificationAPI(
        @Header("Authorization") auth: String,
        @Field("eventId") eventId: String,
        @Field("slotId") slotId: String,
        @Field("date") date: String
    ): NotificationRes?

    @PUT(Constants.TICKETS)
    suspend fun ticketsAPI(
        @Header("Authorization") auth: String,
        @Body data: HashMap<String, Any>
    ): TicketVerifyRes?

    @PATCH(Constants.ENTRY)
    suspend fun entryAPI(
        @Header("Authorization") auth: String,
        @Body data: HashMap<String, Any>
    ): EntryHomeRes?

    @GET(Constants.SCAN_LIST)
    suspend fun scanListAPI(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ): ScanEventListRes
}