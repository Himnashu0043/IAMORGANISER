package com.example.imserviceprovider.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.iamorganiser.base.BaseFragment
import com.example.iamorganiser.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    override fun getLayout(): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        binding.viewPagerWalkThrough.adapter = pagerAdapter

    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment = SubOnboardingFragment.get(position)
    }

}