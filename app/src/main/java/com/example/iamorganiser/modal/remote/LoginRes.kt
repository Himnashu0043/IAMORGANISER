package com.example.iamorganiser.modal.remote

data class LoginRes(
    var code: Int?,
    var data: Data?,
    var message: String?,
    var success: Boolean?
) {
    data class Data(
        var __v: Int?,
        var _id: String?,
        var additionalSkills: List<Any?>?,
        var address: String?,
        var age: Any?,
        var approvalStatus: String?,
        var approvedOn: String?,
        var bankAccount: BankAccount?,
        var bio: String?,
        var bodyType: String?,
        var createdAt: String?,
        var delete: Boolean?,
        var dob: Any?,
        var documents: Documents?,
        var email: String?,
        var experience: String?,
        var eyeColor: String?,
        var featuredLink: List<Any?>?,
        var firstName: String?,
        var hairColor: String?,
        var height: Height?,
        var images: List<Any?>?,
        var isApproved: Boolean?,
        var isOnline: Boolean?,
        var jobsCompleted: Int?,
        var jwtToken: String?,
        var languages: List<Any?>?,
        var lastName: String?,
        var location: Location?,
        var nationality: String?,
        var otp: Any?,
        var password: String?,
        var phone: String?,
        var phoneCountryCode: String?,
        var professionalName: String?,
        var profileImage: String?,
        var profileViews: Int?,
        var projectRates: List<Any?>?,
        var resume: String?,
        var roleCategory: List<Any?>?,
        var skills: List<Any?>?,
        var skinColor: String?,
        var socialMedia: SocialMedia?,
        var specialFeatures: String?,
        var status: String?,
        var totalBooking: Int?,
        var totalEventBooking: Int?,
        var totalEventSpend: Int?,
        var totalIMCommission: Int?,
        var totalPayment: Int?,
        var tribe: String?,
        var updatedAt: String?,
        var userId: String?,
        var userRating: Int?,
        var userRatingCount: Int?,
        var userType: String?,
        var verifyStatus: String?,
        var videos: List<Any?>?,
        var walletAvailableBalance: Int?,
        var walletBalance: Int?,
        var walletRequired: Boolean?
    ) {
        data class BankAccount(
            var accountNo: String?,
            var bank: String?,
            var name: String?,
            var swiftCode: String?
        )

        data class Documents(
            var NICq: NIC?,
            var drivingLicense: DrivingLicense?,
            var guildId: GuildId?,
            var passport: Passport?,
            var studentId: StudentId?
        ) {
            data class NIC(
                var docBack: String?,
                var docFront: String?,
                var docNumber: String?
            )

            data class DrivingLicense(
                var docBack: String?,
                var docFront: String?,
                var docNumber: String?
            )

            data class GuildId(
                var docBack: String?,
                var docFront: String?,
                var docNumber: String?
            )

            data class Passport(
                var docBack: String?,
                var docFront: String?,
                var docNumber: String?
            )

            data class StudentId(
                var docBack: String?,
                var docFront: String?,
                var docNumber: String?
            )
        }

        data class Height(
            var feet: Any?,
            var inch: Any?
        )

        data class Location(
            var type: String?
        )

        data class SocialMedia(
            var facebook: String?,
            var instagram: String?,
            var linkedin: String?,
            var snapchat: String?,
            var tiktok: String?,
            var twitter: String?
        )
    }
}