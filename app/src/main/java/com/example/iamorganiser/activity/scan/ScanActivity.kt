package com.example.iamorganiser.activity.scan

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.ScanMode
import com.example.iamorganiser.activity.booking.BookingConfrimActivity

import com.example.iamorganiser.databinding.ActivityScanBinding
import com.example.iamorganiser.helper.CommonUtil
import org.json.JSONObject

class ScanActivity : AppCompatActivity() {
    lateinit var binding: ActivityScanBinding
    private lateinit var mCodeScanner: CodeScanner
    private var eventId: String? = ""
    private var timeId: String? = ""
    private var scanId: String? = ""
    private var date: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventId = intent.getStringExtra("eventId") ?: ""
        timeId = intent.getStringExtra("timeId") ?: ""
        date = intent.getStringExtra("date") ?: ""
        println("=======scanEv$eventId")
        println("=======scantimeId$timeId")
        println("=======scandate$date")
        initView()
        listener()
    }

    private fun listener() {
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if (CommonUtil.checkCameraPermission(this)) {
                qrCodeScanner()
            } else {
                CommonUtil.requestCamFilePermission(this)
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (CommonUtil.checkCameraFilePermission2(this)) {
                qrCodeScanner()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        CAMERA,
                        READ_MEDIA_IMAGES
                    ),
                    CommonUtil.CAMERA_REQUEST_CODE
                )
            }

        } else {
            Log.e("TAG", "onCreate:Not calling ")
        }
    }

    fun qrCodeScanner() {
        mCodeScanner = CodeScanner(this, binding.scannerView)
        mCodeScanner.camera = CodeScanner.CAMERA_BACK
        mCodeScanner.formats = CodeScanner.ALL_FORMATS
        mCodeScanner.autoFocusMode = AutoFocusMode.SAFE
        mCodeScanner.scanMode = ScanMode.SINGLE
        mCodeScanner.isAutoFocusEnabled = true
        mCodeScanner.isFlashEnabled = false

        mCodeScanner.setDecodeCallback { result ->
            /* val jsonObject = JSONObject(qrData)*/
            try {
                val qrData = result.toString()
                println("=======================$qrData")
                if (!qrData.isNullOrEmpty()) {
                    scanId = qrData
                    startActivity(
                        Intent(this, BookingConfrimActivity::class.java).putExtra(
                            "eventId",
                            eventId
                        )
                            .putExtra("timeId", timeId).putExtra("date", date)
                            .putExtra("scanId", scanId!!.replace("\"",""))
                    )
                } else {
                    CommonUtil.showSnackBar(this, "QR Code is Not Valid!")
                    finish()
                }
            } catch (e: Exception) {

            }


        }


        mCodeScanner.setErrorCallback { error ->
            Log.e("TAG", "inits: ${error.printStackTrace()}")
        }
    }

    override fun onResume() {
        mCodeScanner.startPreview()
        super.onResume()
    }

    override fun onStop() {
        mCodeScanner.stopPreview()
        super.onStop()
    }
}