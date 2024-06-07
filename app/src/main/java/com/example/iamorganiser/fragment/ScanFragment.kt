package com.example.iamorganiser.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iamorganiser.R
import com.example.iamorganiser.activity.booking.BookingConfrimActivity
import com.example.iamorganiser.activity.eventDetail.EventDetailActivity
import com.example.iamorganiser.adapter.HomeAdapter
import com.example.iamorganiser.adapter.ScanAdapter
import com.example.iamorganiser.constants.Constants
import com.example.iamorganiser.databinding.FragmentScanBinding
import com.example.iamorganiser.databinding.HomeDateTimeBottomSheetBinding
import com.example.iamorganiser.helper.ErrorUtil
import com.example.iamorganiser.helper.Loaders
import com.example.iamorganiser.helper.setFormatDate
import com.example.iamorganiser.helper.shortToast
import com.example.iamorganiser.modal.remote.home.ScanlistRes
import com.example.iamorganiser.modal.remote.scan.ScanEventListRes
import com.example.iamorganiser.util.App
import com.example.iamorganiser.util.Resource
import com.example.iamorganiser.viewModel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class ScanFragment : Fragment(),ScanAdapter.ScanDate {
    lateinit var binding: FragmentScanBinding
    var adpter: ScanAdapter? = null
    private var idList = ArrayList<String>()
    private var scanList = ArrayList<ScanEventListRes.Data>()
    private var idHashMapID: HashMap<String, String> = HashMap()
    private var get_id: String = ""

    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var dateSlotBottom: HomeDateTimeBottomSheetBinding
    private var dateList = ArrayList<String>()
    private var dateHashMapID: HashMap<String, String> = HashMap()

    private var timeList = ArrayList<String>()
    private var timeHashMapID: HashMap<String, String> = HashMap()
    private var date: String = ""
    private var getId: String = ""
    private var getTimeId: String = ""
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanBinding.inflate(layoutInflater)
        initView()
        lstner()
        return binding.root
    }

    private fun initView() {
        idList.clear()
        idList.add(0, "Select Event")
        dateList.clear()
        dateList.add(0, "Select Date")
        timeList.clear()
        timeList.add(0, "Select Time")
        observer()
        flatSpinner()

    }

    private fun eventHome() {
        val token = App.app.prefManager.accessToken
        viewModel.eventHome(token, 1, 10, "")
    }

    private fun observer() {
        viewModel.eventHomeLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(requireContext())
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        idList.clear()
                        idList.add(0, "Select Event")
                        it.value.data?.get(0)?.paginationData?.forEach {
                            idList.add(it.eventTitle ?: "")
                            idHashMapID.put(it.eventTitle ?: "", it._id ?: "")
                        }
                    } else if (it.value?.code == Constants.STATUS_404) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        requireActivity().shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    requireActivity().shortToast("Something Went Wrong")
                }
            }
        }
        /*viewModel.scanEventLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(requireContext())
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        scanList.clear()
                        scanList.addAll(it.value.data!!)
                        binding.rcy.layoutManager = LinearLayoutManager(requireContext())
                        adpter = ScanAdapter(requireContext(), scanList)
                        binding.rcy.adapter = adpter
                        adpter!!.notifyDataSetChanged()
                    } else if (it.value?.code == Constants.STATUS_404) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        requireActivity().shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    requireActivity().shortToast("Something Went Wrong")
                }
            }
        }*/
        viewModel.scanEventListLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(requireContext())
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        scanList.clear()
                        scanList.addAll(it.value.data!!)
                        binding.rcy.layoutManager = LinearLayoutManager(requireContext())
                        adpter = ScanAdapter(requireContext(), scanList,this)
                        binding.rcy.adapter = adpter
                        adpter!!.notifyDataSetChanged()
                    } else if (it.value?.code == Constants.STATUS_404) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        requireActivity().shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    requireActivity().shortToast("Something Went Wrong")
                }
            }
        }
        viewModel.eventDateHomeLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(requireContext())
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        if (it.value.data!!.isEmpty()) {
                            requireActivity().shortToast("Date Not Found!!")
                        } else {
                            dateList.clear()
                            dateList.add(0, "Select Date")
                            it.value.data?.forEach {
                                val key = setFormatDate(it.toString()).toString()
                                dateList.add(key)
                                dateHashMapID[key] = it.toString()
                            }
                            println("===================$dateHashMapID")
                        }

                    } else if (it.value?.code == Constants.STATUS_404) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        requireActivity().shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    requireActivity().shortToast("Something Went Wrong")
                }
            }
        }
        viewModel.eventTimeHomeLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Loaders.show(requireContext())
                    Log.d("TAG", "observer: Loading")
                }
                is Resource.Success -> {
                    Loaders.hide()
                    if (it.value?.code == Constants.SUCCESS) {
                        timeList.clear()
                        timeList.add(0, "Select Time")
                        it.value.data?.forEach {
                            timeList.add("${it.startTime ?: ""} - ${it.endTime ?: ""}")
                            timeHashMapID.put(
                                "${it.startTime ?: ""} - ${it.endTime ?: ""}", it._id ?: ""
                            )
                        }
                    } else if (it.value?.code == Constants.STATUS_404) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_401) {
                        requireActivity().shortToast(it.value.message.toString())
                    } else if (it.value?.code == Constants.STATUS_FAILURE) {
                        requireActivity().shortToast(it.value.message.toString())
                    }
                }
                is Resource.Failure -> {
                    Loaders.hide()
                    ErrorUtil.handlerGeneralError(binding.root, it.throwable)
                }
                else -> {
                    Loaders.hide()
                    requireActivity().shortToast("Something Went Wrong")
                }
            }
        }
    }

    private fun lstner() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(requireContext(), BookingConfrimActivity::class.java))
        }
    }

    private fun flatSpinner() {
        // val genderList = resources.getStringArray(R.array.EditProfile)
        binding.flatSpiner.adapter =
            ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, idList)
        /*ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, genderList)*/
        binding.flatSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (binding.flatSpiner.selectedItemPosition > 0) {
                    get_id =
                        idHashMapID.get(binding.flatSpiner.selectedItem.toString())
                            .toString()
                    println("---get_id$get_id")
                    binding.rcy.visibility = View.VISIBLE
                    val token = App.app.prefManager.accessToken
                    viewModel.scanEventList(token, get_id)
                } else {
                    binding.rcy.visibility = View.INVISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onResume() {
        super.onResume()
        eventHome()
    }
    private fun dateSlotBottomSheet() {
        dateSlotBottom =
            HomeDateTimeBottomSheetBinding.inflate(LayoutInflater.from(requireContext()))
        bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme).apply {
                setContentView(dateSlotBottom.root)
                dateSlotBottom.dateSpiner.adapter =
                    ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, dateList)
                dateSlotBottom.dateSpiner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long,
                        ) {

                            if (dateSlotBottom.dateSpiner.selectedItemPosition > 0) {
                                var date1 = dateList.get(position)
                                date = dateHashMapID.get(date1).toString()
                                println("=======$date")
                                val token = App.app.prefManager.accessToken
                                viewModel.eventTimeHome(token, getId, date)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }

                dateSlotBottom.timeSpiner.adapter =
                    ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, timeList)
                dateSlotBottom.timeSpiner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long,
                        ) {
                            if (dateSlotBottom.timeSpiner.selectedItemPosition > 0) {
                                getTimeId =
                                    timeHashMapID.get(dateSlotBottom.timeSpiner.selectedItem.toString())
                                        .toString()
                            }


                            println("===============$getTimeId")
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }






                dateSlotBottom.btnContinue.setOnClickListener {
                    if (dateList.isNotEmpty()) {
                        bottomSheetDialog.dismiss()
                        startActivity(
                            Intent(requireContext(), EventDetailActivity::class.java).putExtra(
                                "id",
                                getId
                            ).putExtra("timeId", getTimeId).putExtra("date", date)
                        )
                    } else {
                        bottomSheetDialog.dismiss()
                    }
                }

            }
        bottomSheetDialog.show()
    }

    override fun onClickDate(id: String, position: Int) {
        getId = id
        val token = App.app.prefManager.accessToken
        viewModel.eventDateHome(token, id)
        dateSlotBottomSheet()
    }
}