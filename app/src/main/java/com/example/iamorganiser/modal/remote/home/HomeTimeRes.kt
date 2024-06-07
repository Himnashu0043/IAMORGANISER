package com.example.iamorganiser.modal.remote.home

data class HomeTimeRes(
    var code: Int?,
    var data: ArrayList<Data>?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var __v: Int?,
        var _id: String?,
        var createdAt: String?,
        var endTime: String?,
        var eventId: String?,
        var startTime: String?,
        var startTimeInMinutes: Int?,
        var updatedAt: String?
    )
}