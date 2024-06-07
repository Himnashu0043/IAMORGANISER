package com.example.iamorganiser.modal.remote.home

data class EntryHomeRes(
    var code: Int?,
    var data: ArrayList<Data>?,
    var message: String?,
    var success: Boolean?
) : java.io.Serializable {
    data class Data(
        var __v: Int?,
        var _id: String?,
        var age: Int?,
        var checkInBy: String?,
        var checkInTime: String?,
        var createdAt: String?,
        var email: String?,
        var isPrimary: Boolean?,
        var is_checkedIn: Boolean?,
        var name: String?,
        var phoneNumber: String?,
        var ticketId: String?,
        var updatedAt: String?
    ) : java.io.Serializable
}