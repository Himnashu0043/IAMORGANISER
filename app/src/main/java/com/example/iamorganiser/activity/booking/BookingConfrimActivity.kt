package com.example.iamorganiser.activity.booking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iamorganiser.MainActivity
import com.example.iamorganiser.activity.cong.CongratulationActivity
import com.example.iamorganiser.adapter.PersonAdapter
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityBookingConfrimBinding
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.setFormatDate
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.remote.home.EntryHomeRes
import com.example.iamorganiser.modal.remote.home.TicketVerifyRes
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel

class BookingConfrimActivity : AppCompatActivity() {
    private var primaryId: String? = ""
    lateinit var binding: ActivityBookingConfrimBinding
    private var list = ArrayList<TicketVerifyRes.Data.TicketAttendee>()
    private var eventId: String? = ""
    private var timeId: String? = ""
    private var scanId: String? = ""
    private var date: String? = ""
    private var adpterq: PersonAdapter? = null
    private var id_List = ArrayList<String>()
    private var entryAllowList = ArrayList<EntryHomeRes.Data>()
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingConfrimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventId = intent.getStringExtra("eventId") ?: ""
        timeId = intent.getStringExtra("timeId") ?: ""
        scanId = intent.getStringExtra("scanId") ?: ""
        date = intent.getStringExtra("date") ?: ""
        println("=======scanEv$eventId")
        println("=======scantimeId$timeId")
        println("=======scandate$date")
        println("=======scanscanId$scanId")
        initView()
        lstnr()
    }

    private fun initView() {
        observer()


    }

    private fun observer() {
        viewModel.ticketVerifyLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        list.clear()
                        binding.textView261.text = it.value.data?.get(0)?.bookingNumber ?: ""
                        binding.textView27.text = it.value.data?.get(0)?.event?.eventTitle ?: ""
                        binding.textView41.text =
                            it.value.data?.get(0)?.event?.eventLocation?.locationName ?: ""
                        binding.tvTime.text =
                            setFormatDate(it.value.data?.get(0)?.event?.eventStartDate ?: "")

                        for (test in it.value.data?.get(0)?.ticketAttendees!!) {
                            if (test.isPrimary != true) {
                                list.add(test)
                                println("=============TEst$list")
                                binding.rcy.layoutManager = LinearLayoutManager(this)
                                adpterq = PersonAdapter(this, list)
                                binding.rcy.adapter = adpterq
                                adpterq!!.notifyDataSetChanged()

                            } else {
                                if (test.is_checkedIn == true) {
                                    binding.textView28.text = test.name ?: ""
                                    binding.tvYear.text = "${test.age ?: 0},year"
                                    primaryId = test._id ?: ""
                                    binding.tvChekedIn.text = "Checked In"
                                    binding.tvDateAllowBy.text =
                                        "Date : ${setFormatDate(test.checkInTime)} allow by ${
                                            test.userData?.get(0)?.firstName ?: ""
                                        } ${test.userData?.get(0)?.lastName ?: ""}"
                                } else {
                                    binding.textView28.text = test.name ?: ""
                                    binding.tvYear.text = "${test.age ?: 0},year"
                                    primaryId = test._id ?: ""

                                }


                                /* Glide.with(this).load(it.value.data?.get(0)?.eventImages?.get(0))
                                     .into(binding.imageView7)*/
                            }
                        }

                        binding.tvsubTotal.text = "$ ${it.value.data?.get(0)?.subTotal ?: 0}"
                        binding.textView3211.text = "$ ${it.value.data?.get(0)?.taxAmount ?: 0}"
                        binding.tvTotal.text = "$ ${it.value.data?.get(0)?.totalAmount ?: 0}"


                    } else if (it.value?.code == Constants.STATUS_404) {
                        shortToast("QR Code is Not Valid!")
                        startActivity(
                            Intent(
                                this,
                                MainActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                        finish()
                    } else if (it.value?.code == Constants.STATUS_401) {
                        shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    shortToast("Something Went Wrong")
                }
            }
        }
        viewModel.entryAllowLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        entryAllowList.clear()
                        entryAllowList.addAll(it.value.data!!)
                        startActivity(
                            Intent(
                                this,
                                CongratulationActivity::class.java
                            ).putExtra("from", "list").putExtra("entry_list", entryAllowList)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                        finish()
                    } else if (it.value?.code == Constants.STATUS_404) {
                        shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    shortToast("Something Went Wrong")
                }
            }
        }
    }

    private fun lstnr() {
        binding.imageView8.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }
        binding.btnScan.setOnClickListener {
//            startActivity(Intent(this, CongratulationActivity::class.java))
            id_List = adpterq?.getList()!!
            if (binding.checkBox.isChecked) {
                id_List.add(primaryId ?: "")
            }
            if (!id_List.isNullOrEmpty()) {
                val token = App.app.prefManager.accessToken
                val params = HashMap<String, Any>()
                params["ticketId"] = scanId.toString()
                params["allowedUsers"] = id_List
                viewModel.entryAllow(token, params)
            } else {
                shortToast("Please Select Person!!")
            }


        }


    }

    override fun onResume() {
        super.onResume()
        val token = App.app.prefManager.accessToken
        val params = HashMap<String, Any>()
        params["eventId"] = eventId.toString()
        params["slotId"] = timeId.toString()
        params["date"] = date.toString()
        params["ticketId"] = scanId.toString()
        viewModel.ticketVerify(token, params)
    }
}