package com.example.iamorganiser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.iamorganiser.repository.AuthRepository
import com.example.iamorganiser.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    /* private val _userLogin: MutableLiveData<Resource<LoginResponse?>?> = MutableLiveData()
     val userLogin: LiveData<Resource<LoginResponse?>?> get() = _userLogin
     suspend fun login(params:HashMap<String,String>){
         _userLogin.value = Resource.Loading
         _userLogin.value = AuthRepository.login(params)
     }

     private val _userSignUp: MutableLiveData<Resource<SignUpResponse?>?> = MutableLiveData()
     val userSignUp: LiveData<Resource<SignUpResponse?>?> get() = _userSignUp
     suspend fun signUp(params:HashMap<String,String>){
         _userSignUp.value = Resource.Loading
         _userSignUp.value = AuthRepository.signUp(params)
     }

     private val _userDetail: MutableLiveData<Resource<LoginResponse?>?> = MutableLiveData()
     val userDetail: LiveData<Resource<LoginResponse?>?> get() = _userDetail
     suspend fun userDetail(token: String){
         _userDetail.value = Resource.Loading
         _userDetail.value = AuthRepository.userDetail(token)
     }

     private val _updateUserDetail: MutableLiveData<Resource<LoginResponse?>?> = MutableLiveData()
     val updateUserDetail: LiveData<Resource<LoginResponse?>?> get() = _updateUserDetail
     fun updateUserDetail(token: String, params: HashMap<String, String>) {
         viewModelScope.launch {
             _updateUserDetail.value = Resource.Loading
             _updateUserDetail.value = AuthRepository.updateUser(token, params)
         }
     }

     private val _privacyPolicy: MutableLiveData<Resource<PrivacyandPolicyRes?>?> = MutableLiveData()
     val _privacyPolicyList: LiveData<Resource<PrivacyandPolicyRes?>?> get() = _privacyPolicy
     fun _privacyPolicyList(token: String, type: String) {
         viewModelScope.launch {
             _privacyPolicy.value = Resource.Loading
             _privacyPolicy.value = AuthRepository.privacyPolicy(token, type)
         }
     }

     private val _examCategoryList: MutableLiveData<Resource<ExamNameListRes?>?> = MutableLiveData()
     val _examcategoryList: LiveData<Resource<ExamNameListRes?>?> get() = _examCategoryList
     fun _exam_CategoryList(token: String) {
         viewModelScope.launch {
             _examCategoryList.value = Resource.Loading
             _examCategoryList.value = AuthRepository.examCategoryList(token)
         }
     }

     private val _faqList: MutableLiveData<Resource<FAQListRes?>?> = MutableLiveData()
     val _faqListLiveData: LiveData<Resource<FAQListRes?>?> get() = _faqList
     fun _faq(token: String) {
         viewModelScope.launch {
             _faqList.value = Resource.Loading
             _faqList.value = AuthRepository.faqList(token)
         }
     }

     private val _myTestList: MutableLiveData<Resource<MyTestListRes?>?> = MutableLiveData()
     val _myTestLiveData: MutableLiveData<Resource<MyTestListRes?>?> get() = _myTestList
     fun _myTest(token: String, examId: String) {
         viewModelScope.launch {
             _myTestList.value = Resource.Loading
             _myTestList.value = AuthRepository.myTestList(token, examId)
         }
     }

     private val _myTestContestList: MutableLiveData<Resource<TestContestListRes?>?> =
         MutableLiveData()
     val _myTestContestLiveData: MutableLiveData<Resource<TestContestListRes?>?> get() = _myTestContestList
     fun _myTestContest(token: String, testId: String) {
         viewModelScope.launch {
             _myTestContestList.value = Resource.Loading
             _myTestContestList.value = AuthRepository.myTestContestList(token, testId)
         }
     }

     private val _getContestEntryList: MutableLiveData<Resource<CommonRes?>?> = MutableLiveData()
     val _getContestEntryLiveData: MutableLiveData<Resource<CommonRes?>?> get() = _getContestEntryList
     fun _getContestEntry(token: String, params: HashMap<String, String>) {
         viewModelScope.launch {
             _getContestEntryList.value = Resource.Loading
             _getContestEntryList.value = AuthRepository.getContestEntry(token, params)
         }
     }

     private val _getMyContestList: MutableLiveData<Resource<MyTestMainFragListRes?>?> =
         MutableLiveData()
     val _getMyContestLiveData: MutableLiveData<Resource<MyTestMainFragListRes?>?> get() = _getMyContestList
     fun _getMyContest(token: String, staus:String?,examcategoryId:String) {
         viewModelScope.launch {
             _getMyContestList.value = Resource.Loading
             _getMyContestList.value = AuthRepository.getMyContest(token, staus,examcategoryId)
         }
     }

     private val _getlogOut: MutableLiveData<Resource<CommonRes?>?> =
         MutableLiveData()
     val _getLogOutLiveData: MutableLiveData<Resource<CommonRes?>?> get() = _getlogOut
     fun _getLogOut(token: String) {
         viewModelScope.launch {
             _getlogOut.value = Resource.Loading
             _getlogOut.value = AuthRepository.getLogOut(token)
         }
     }

     private val _addAmount: MutableLiveData<Resource<CommonRes?>?> =
         MutableLiveData()
     val _addAmountLiveData: MutableLiveData<Resource<CommonRes?>?> get() = _addAmount
     fun _addAmount(token: String,amount:Int) {
         viewModelScope.launch {
             _addAmount.value = Resource.Loading
             _addAmount.value = AuthRepository.addAmount(token,amount)
         }
     }

     private val _getAmount: MutableLiveData<Resource<GetAmountRes?>?> =
         MutableLiveData()
     val _getAmountLiveData: MutableLiveData<Resource<GetAmountRes?>?> get() = _getAmount
     fun _getAmount(token: String) {
         viewModelScope.launch {
             _getAmount.value = Resource.Loading
             _getAmount.value = AuthRepository.getAmount(token)
         }
     }

     private val _detailsQuestion: MutableLiveData<Resource<DetailsQuestionList?>?> =
         MutableLiveData()
     val _detailsQuestionLiveData: MutableLiveData<Resource<DetailsQuestionList?>?> get() = _detailsQuestion
     fun _detailsQuestion(token: String, contestId: String) {
         viewModelScope.launch {
             _detailsQuestion.value = Resource.Loading
             _detailsQuestion.value = AuthRepository.detailsQuestion(token, contestId)
         }
     }
 */
    private val _forgetPassword: MutableLiveData<Resource<ForgetPasswordRes?>?> = MutableLiveData()
    val forgetPasswordLiveData: LiveData<Resource<ForgetPasswordRes?>?> get() = _forgetPassword
    suspend fun forgetPasswordAPI(model: ForgetPasswordPostModel) {
        _forgetPassword.value = Resource.Loading
        _forgetPassword.value = AuthRepository.forgetPassword(model)
    }

    private val _otpVerify: MutableLiveData<Resource<OtpVerifyRes?>?> = MutableLiveData()
    val otpVerifyLiveData: LiveData<Resource<OtpVerifyRes?>?> get() = _otpVerify
    suspend fun otpVerifyAPI(model: OtpVerifyPostModel) {
        _otpVerify.value = Resource.Loading
        _otpVerify.value = AuthRepository.otp(model)
    }

    private val _resetPassword: MutableLiveData<Resource<OtpVerifyRes?>?> = MutableLiveData()
    val resetPasswordLiveData: LiveData<Resource<OtpVerifyRes?>?> get() = _resetPassword
    fun resetPasswordAPI(model: ResetPasswordPostModel) {
        /*  _otpVerify.value = Resource.Loading
          _otpVerify.value = AuthRepository.reset(model)*/
        viewModelScope.launch {
            _resetPassword.value = Resource.Loading
            _resetPassword.value = AuthRepository.reset(model)
        }
    }

    private val _login: MutableLiveData<Resource<LoginRes?>?> = MutableLiveData()
    val loginLiveData: LiveData<Resource<LoginRes?>?> get() = _login
    suspend fun loginAPI(model: ResetPasswordPostModel) {
        _login.value = Resource.Loading
        _login.value = AuthRepository.loginFun(model)
    }

    private val _changePassword: MutableLiveData<Resource<OtpVerifyRes?>?> = MutableLiveData()
    val changePasswordLiveData: LiveData<Resource<OtpVerifyRes?>?> get() = _changePassword
    suspend fun changePasswordAPI(token: String, model: ChanagePasswordPostModel) {
        _changePassword.value = Resource.Loading
        _changePassword.value = AuthRepository.change(token, model)
    }

    private val _logout: MutableLiveData<Resource<OtpVerifyRes?>?> = MutableLiveData()
    val logoutLiveData: MutableLiveData<Resource<OtpVerifyRes?>?> get() = _logout
    fun _logout(token: String) {
        viewModelScope.launch {
            _logout.value = Resource.Loading
            _logout.value = AuthRepository.logout(token)
        }

    }

    private val _eventHome: MutableLiveData<Resource<HomeEventRes?>?> = MutableLiveData()
    val eventHomeLiveData: MutableLiveData<Resource<HomeEventRes?>?> get() = _eventHome
    fun eventHome(token: String, page: Int, limit: Int, eventCategory: String) {
        viewModelScope.launch {
            _eventHome.value = Resource.Loading
            _eventHome.value = AuthRepository.eventHome(token, page, limit, eventCategory)
        }

    }

    private val _eventDateHome: MutableLiveData<Resource<HomeDateRes?>?> = MutableLiveData()
    val eventDateHomeLiveData: MutableLiveData<Resource<HomeDateRes?>?> get() = _eventDateHome
    fun eventDateHome(token: String, id: String) {
        viewModelScope.launch {
            _eventDateHome.value = Resource.Loading
            _eventDateHome.value = AuthRepository.eventDateHome(token, id)
        }

    }

    private val _eventTimeHome: MutableLiveData<Resource<HomeTimeRes?>?> = MutableLiveData()
    val eventTimeHomeLiveData: MutableLiveData<Resource<HomeTimeRes?>?> get() = _eventTimeHome
    fun eventTimeHome(token: String, id: String, date: String) {
        viewModelScope.launch {
            _eventTimeHome.value = Resource.Loading
            _eventTimeHome.value = AuthRepository.eventTimeHome(token, id, date)
        }

    }

    private val _scanEvent: MutableLiveData<Resource<ScanlistRes?>?> = MutableLiveData()
    val scanEventLiveData: MutableLiveData<Resource<ScanlistRes?>?> get() = _scanEvent
    fun scanEvent(token: String, id: String, slotId: String, date: String) {
        viewModelScope.launch {
            _scanEvent.value = Resource.Loading
            _scanEvent.value = AuthRepository.scanList(token, id, slotId, date)
        }

    }

    private val _notification: MutableLiveData<Resource<NotificationRes?>?> = MutableLiveData()
    val notificationLiveData: MutableLiveData<Resource<NotificationRes?>?> get() = _notification
    fun noti(token: String, eventId: String, slotId: String, date: String) {
        viewModelScope.launch {
            _notification.value = Resource.Loading
            _notification.value = AuthRepository.noti(token, eventId, slotId, date)
        }

    }

    private val _ticketVerify: MutableLiveData<Resource<TicketVerifyRes?>?> = MutableLiveData()
    val ticketVerifyLiveData: MutableLiveData<Resource<TicketVerifyRes?>?> get() = _ticketVerify
    fun ticketVerify(
        token: String,
        params: HashMap<String,Any>
    ) {
        viewModelScope.launch {
            _ticketVerify.value = Resource.Loading
            _ticketVerify.value =
                AuthRepository.ticketsVerify(token,params)
        }

    }

    private val _entryAllow: MutableLiveData<Resource<EntryHomeRes?>?> = MutableLiveData()
    val entryAllowLiveData: MutableLiveData<Resource<EntryHomeRes?>?> get() = _entryAllow
    fun entryAllow(
        token: String,
        params: HashMap<String,Any>
    ) {
        viewModelScope.launch {
            _entryAllow.value = Resource.Loading
            _entryAllow.value =
                AuthRepository.entryAllow(token,params)
        }

    }

    private val _scanEventList: MutableLiveData<Resource<ScanEventListRes?>?> = MutableLiveData()
    val scanEventListLiveData: MutableLiveData<Resource<ScanEventListRes?>?> get() = _scanEventList
    fun scanEventList(
        token: String,
        id:String
    ) {
        viewModelScope.launch {
            _scanEventList.value = Resource.Loading
            _scanEventList.value =
                AuthRepository.scanEventList(token,id)
        }

    }
}