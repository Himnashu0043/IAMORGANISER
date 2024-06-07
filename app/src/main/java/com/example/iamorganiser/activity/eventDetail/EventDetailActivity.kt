package com.example.iamorganiser.activity.eventDetail

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.iamorganiser.R
import com.example.iamorganiser.activity.cong.CongratulationNotiActivity
import com.example.iamorganiser.activity.scan.ScanActivity
import com.example.iamorganiser.adapter.*
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.ActivityEventDetailBinding
import com.example.iamorganiser.helper.*
import com.example.iamorganiser.modal.MoreModal
import com.example.iamorganiser.modal.remote.home.ScanlistRes
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import java.util.*
import kotlin.collections.ArrayList

class EventDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityEventDetailBinding
    private var moderAdapter: MoreAdapter? = null
    private var ticketAdapter: TicketTypeAdapter? = null
    private var ticketList = ArrayList<ScanlistRes.Data.EventTicketType>()
    private var id: String = ""
    private var getTimeId: String = ""
    private var date: String = ""
    private var latitude: Double = 0.0
    private var logitude: Double = 0.0
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    var currentPos = 0
    val handler = Handler()
    var currentPosImg = 0
    val handlerImg = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getStringExtra("id") ?: ""
        getTimeId = intent.getStringExtra("timeId") ?: ""
        date = intent.getStringExtra("date") ?: ""
        println("========$id")
        initView()
        lstnr()
    }

    private fun initView() {
        observer()


    }


    private fun observer() {
        viewModel.scanEventLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        latitude = it.value.data?.get(0)?.eventLocation?.coordinates?.get(1)!!
                        logitude = it.value.data?.get(0)?.eventLocation?.coordinates?.get(0)!!
                        Glide.with(this).load(it.value.data?.get(0)?.eventImages?.get(0))
                            .into(binding.imageView7)
                        binding.textView14.text = it.value.data?.get(0)?.eventTitle ?: ""
                        binding.textView41.text =
                            it.value.data?.get(0)?.eventLocation?.locationName ?: ""
                        binding.textView411.text =
                            "${setFormatDate(it.value.data?.get(0)?.eventStartDate ?: "")} - ${
                                setFormatDate(it.value.data?.get(0)?.eventEndDate ?: "")
                            }"
                        val htmlAsString = it.value.data?.get(0)?.eventDescription ?: ""
                        val htmlAsSpanned = Html.fromHtml(htmlAsString)
                        binding.textView16.setTextWithReadMore(htmlAsSpanned.toString())
                        val htmlAsString1 = it.value.data?.get(0)?.whyToAttendEvent ?: ""
                        val htmlAsSpanned1 = Html.fromHtml(htmlAsString1)
                        binding.textView18.text = htmlAsSpanned1
                        val (hours, minutes) = convertMinutesToHoursAndMinutes(
                            it.value.data?.get(0)?.eventDuration ?: 0
                        )
                        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
                        moderAdapter = MoreAdapter(
                            this,
                            more(
                                it.value.data?.get(0)?.eventGenre ?: "",
                                it.value.data?.get(0)?.eventLanguages?.get(0) ?: "",
                                if (minutes == 0) {
                                    "$hours hr"
                                } else {
                                    "$hours hr $minutes Mins"
                                },
                                it.value.data?.get(0)?.attendentAgeGroup ?: ""
                            )
                        )
                        binding.recyclerView.adapter = moderAdapter
                        moderAdapter!!.notifyDataSetChanged()
                        ticketList.clear()
                        ticketList.addAll(it.value.data?.get(0)?.eventTicketTypes!!)
                        binding.rcy.layoutManager = LinearLayoutManager(this)
                        ticketAdapter = TicketTypeAdapter(this, ticketList)
                        binding.rcy.adapter = ticketAdapter
                        ticketAdapter!!.notifyDataSetChanged()
                        binding.textView35.text =
                            (it.value.data?.get(0)?.totalTicketSold ?: 0).toString()
                        binding.textView351.text =
                            (it.value.data?.get(0)?.totalAttendees ?: 0).toString()
                        binding.viewPager1.adapter =
                            ViewPagerVideoAdapter(
                                this,
                                it.value.data?.get(0)?.eventVideo!!
                            )
                        binding.tabLayout1.setupWithViewPager(binding.viewPager1, true)
                        val runnable = Runnable {
                            if (currentPos == it.value.data?.get(0)?.eventImages!!.size - 1) currentPos =
                                0
                            else currentPos++
                            if (binding.viewPager1 != null) {
                                binding.viewPager1.setCurrentItem(currentPos, true)
                            }
                        }

                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                handler.post(runnable)
                            }
                        }, CommonUtil.DELAY_MS, CommonUtil.PERIOD_MS)


                        binding.viewPager2.adapter =
                            ViewPagerAdapter(this, it.value.data?.get(0)?.eventImages!!)
                        binding.tabLayout2.setupWithViewPager(binding.viewPager2, true)
                        val runnable1 = Runnable {
                            if (currentPosImg == it.value.data?.get(0)?.eventImages!!.size - 1) currentPosImg =
                                0
                            else currentPosImg++
                            if (binding.viewPager2 != null) {
                                binding.viewPager2.setCurrentItem(currentPosImg, true)
                            }
                        }

                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                handlerImg.post(runnable1)
                            }
                        }, CommonUtil.DELAY_MS, CommonUtil.PERIOD_MS)


                        Glide.with(this).load(it.value.data?.get(0)?.eventArtistImage ?: "")
                            .into(binding.imageView111)
                        binding.tvArtist.text = it.value.data?.get(0)?.eventArtistName ?: ""
                        binding.tvArtistDes.text = it.value.data?.get(0)?.eventGenre ?: ""
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
        viewModel.notificationLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(this)
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        if (it.value.message == "Ticket Attendees Not Found") {
                            shortToast(it.value.message.toString())
                        } else {
                            startActivity(
                                Intent(this, CongratulationNotiActivity::class.java).setFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_CLEAR_TASK
                                )
                            )
                            finish()
                        }
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

    private fun more(
        genName: String,
        lang: String,
        duration: String,
        age: String
    ): ArrayList<MoreModal> {
        return arrayListOf<MoreModal>().apply {
            add(MoreModal(R.drawable.gen_icon, "Genre", "$genName"))
            add(MoreModal(R.drawable.lang_icon, "Language", "$lang"))
            add(MoreModal(R.drawable.duration_icon, "Duration", "$duration"))
            add(MoreModal(R.drawable.age_icon, "Age", "$age"))
        }
    }

    private fun lstnr() {
        binding.imageView6.setOnClickListener {
            finish()
        }
        binding.btnNoti.setOnClickListener {
            val token = App.app.prefManager.accessToken
            viewModel.noti(token, id, getTimeId, date)

        }
        binding.btnLogin.setOnClickListener {
            sendToMap(this, latitude.toString(), logitude.toString())
        }
        binding.btnScan.setOnClickListener {
            startActivity(
                Intent(this, ScanActivity::class.java).putExtra("eventId", id)
                    .putExtra("timeId", getTimeId).putExtra("date", date)
            )
        }

    }

    override fun onResume() {
        super.onResume()
        val token = App.app.prefManager.accessToken
        viewModel.scanEvent(token, id, getTimeId, date)
    }

    fun sendToMap(context: Activity, latitude: String, longitude: String) {
        val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
        intent.setPackage("com.google.android.apps.maps")
        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            try {
                val unrestrictedIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
                context.startActivity(unrestrictedIntent)
            } catch (innerEx: ActivityNotFoundException) {
                Toast.makeText(context, "Please install a maps application", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}