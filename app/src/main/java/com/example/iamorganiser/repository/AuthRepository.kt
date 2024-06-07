package com.example.iamorganiser.repository


import com.example.iamorganiser.baseRepo.BaseRepository
import com.example.iamorganiser.modal.local.ChanagePasswordPostModel
import com.example.iamorganiser.modal.local.ForgetPasswordPostModel
import com.example.iamorganiser.modal.local.OtpVerifyPostModel
import com.example.iamorganiser.modal.local.ResetPasswordPostModel
import com.example.iamorganiser.network.RestClient

object AuthRepository : BaseRepository() {

    /*suspend fun login(params: HashMap<String,String>) = safeApiCall {
        RestClient().getApi().login(params)
    }

    suspend fun signUp(params: HashMap<String,String>) = safeApiCall {
        RestClient().getApi().signUp(params)
    }

    suspend fun userDetail(token: String) = safeApiCall {
        RestClient().getApi().getUserDetail(token)
    }

    suspend fun updateUser(token: String,params: HashMap<String,String>) = safeApiCall {
        RestClient().getApi().updateUserDetail(token,params)
    }
    suspend fun privacyPolicy(token: String,type:String) = safeApiCall {
        RestClient().getApi().getstatiContent(token,type)
    }
    suspend fun examCategoryList(token: String) = safeApiCall {
        RestClient().getApi().getExamCategoryList(token)
    }
    suspend fun faqList(token: String) = safeApiCall {
        RestClient().getApi().getFaqList(token)
    }

    suspend fun myTestList(token: String,examId:String) = safeApiCall {
        RestClient().getApi().getTestList(token,examId)
    }
    suspend fun myTestContestList(token: String,testId:String) = safeApiCall {
        RestClient().getApi().getTestConyesyList(token,testId)
    }

    suspend fun getContestEntry(token: String,params: HashMap<String,String>) = safeApiCall {
        RestClient().getApi().getContestEntryList(token,params)
    }
    suspend fun getMyContest(token: String,staus:String?,examcategoryId:String) = safeApiCall {
        RestClient().getApi().getMyContestList(token,staus,examcategoryId)
    }

    suspend fun getLogOut(token: String) = safeApiCall {
        RestClient().getApi().logOut(token)
    }

    suspend fun addAmount(token: String, amount: Int) = safeApiCall {
        RestClient().getApi().addAmount(token, amount)
    }

    suspend fun getAmount(token: String) = safeApiCall {
        RestClient().getApi().getAmount(token)
    }

    suspend fun detailsQuestion(token: String, contestId: String) = safeApiCall {
        RestClient().getApi().detailsQuestion(token, contestId)
    }
*/
    suspend fun forgetPassword(model: ForgetPasswordPostModel) = safeApiCall {
        RestClient().getApi().forgetPassword(model)
    }

    suspend fun otp(model: OtpVerifyPostModel) = safeApiCall {
        RestClient().getApi().otpVerify(model)
    }

    suspend fun reset(model: ResetPasswordPostModel) = safeApiCall {
        RestClient().getApi().resetPassword(model)
    }

    suspend fun loginFun(model: ResetPasswordPostModel) = safeApiCall {
        RestClient().getApi().login(model)
    }

    suspend fun change(token: String, model: ChanagePasswordPostModel) = safeApiCall {
        RestClient().getApi().changePassword(token, model)
    }

    suspend fun logout(token: String) = safeApiCall {
        RestClient().getApi().logoutAPI(token)
    }

    suspend fun eventHome(token: String, page: Int, limit: Int, eventCategory: String) =
        safeApiCall {
            RestClient().getApi().eventHomeAPI(token, page, limit, eventCategory)
        }
    suspend fun eventDateHome(token: String, id: String) =
        safeApiCall {
            RestClient().getApi().eventDateHomeAPI(token,id)
        }
    suspend fun eventTimeHome(token: String, id: String,date:String) =
        safeApiCall {
            RestClient().getApi().eventTimeHomeAPI(token,id,date)
        }

    suspend fun scanList(token: String, id: String,slotId:String,date: String) =
        safeApiCall {
            RestClient().getApi().scanAPI(token, id,slotId,date)
        }

    suspend fun noti(token: String, eventId: String,slotId:String,date: String) =
        safeApiCall {
            RestClient().getApi().notificationAPI(token, eventId,slotId,date)
        }
    suspend fun ticketsVerify(token: String, param :HashMap<String,Any>) =
        safeApiCall {
            RestClient().getApi().ticketsAPI(token,param)
        }
    suspend fun entryAllow(token: String, param :HashMap<String,Any>) =
        safeApiCall {
            RestClient().getApi().entryAPI(token,param)
        }

    suspend fun scanEventList(token: String, id:String) =
        safeApiCall {
            RestClient().getApi().scanListAPI(token,id)
        }
}