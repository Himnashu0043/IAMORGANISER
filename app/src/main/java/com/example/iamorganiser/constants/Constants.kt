package com.example.iamorganiser.constants

object Constants {

    const val BASE_URL = "http://13.58.147.138:5015/api/v1/organiser/"
    const val FORGET_PASSWORD = "forgetPassword"
    const val OTP_VERIFY = "forgetPasswordOtpVerify"
    const val RESET_PASSWORD = "resetPassword"
    const val LOGIN = "login"
    const val CHANGE_PASSWORD = "passwordChange"
    const val LOGOUT = "logout"
    const val EVENTS = "events"
    const val IDEVENTS = "events/{id}"
    const val EVENT_SLOT_DATE = "events/slotDate/{id}"
    const val EVENT_SLOT_TIME = "events/slotTime/{id}"
    const val NOTIFICATION = "events/notification"
    const val TICKETS = "events/ticket"
    const val ENTRY = "events/ticket/entry"
    const val SCAN_LIST = "events/scan/{id}"
   /* const val LOGIN = "user/login"
    const val SIGNUP = "user/signup"
    const val DELETE = "user/delete"
    const val LIST = "user/list"
    const val LOGOUT = "user/logout"
    const val UPDATE_PROFILE = "user/update"
    const val STATIC_CONTENT = "user/static-content/list"
    const val EXAM_CATEGORY_LIST = "user/home/exam-category-list"
    const val FAQ_LIST = "user/static-content/faq-list"
    const val TEST_LIST = "user/home/test-list"
    const val TEST_CONTEST = "user/home/test-contest"
    const val CONTEST_ENTRY = "user/home/contest-entry"
    const val MYCONTEST = "user/home/my-contest"
    const val ADD_AMOUNT = "user/wallet/add-amount"
    const val AMOUNT = "user/wallet/amount"
    const val DETAILS = "user/contest/detail"
*/
    const val SUCCESS = 200
    const val STATUS_FAILURE = 400
    const val STATUS_404 = 404
    const val STATUS_401 = 401
    const val STATUS_409 = 409
    const val STATUS_501 = 501
    const val STATUS_500 = 500
    const val STATUS_503 = 503

}