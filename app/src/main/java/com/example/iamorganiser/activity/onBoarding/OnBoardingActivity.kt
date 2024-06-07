package com.example.iamorganiser.activity.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iamorganiser.R
import com.example.iamorganiser.base.BaseActivity
import com.example.iamorganiser.databinding.ActivityOnBoardingBinding
import com.example.iamorganiser.helper.CommonUtil
import com.example.iamorganiser.helper.PrefrencesHelper
import com.example.imserviceprovider.fragments.OnboardingFragment

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    override fun getLayout(): ActivityOnBoardingBinding {
        return ActivityOnBoardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CommonUtil.setTransparentStatusBarOnly(this)
        CommonUtil.clearLightStatusBar(this)
        PrefrencesHelper.saveTutorialStatus(this, false)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, OnboardingFragment())
            .commit()
    }
}