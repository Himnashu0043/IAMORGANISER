package com.example.imserviceprovider.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.iamorganiser.R
import com.example.iamorganiser.activity.auth.LoginActivity
import com.example.iamorganiser.base.BaseFragment
import com.example.iamorganiser.databinding.FragmentSubOnboardingBinding

class SubOnboardingFragment private constructor() : BaseFragment<FragmentSubOnboardingBinding>(),
    View.OnClickListener {

    private var fragPosition: Int = 0
    private lateinit var onboardingFragment: OnboardingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragPosition = it.getInt("position", 0)
        }
    }

    override fun getLayout(): FragmentSubOnboardingBinding {
        return FragmentSubOnboardingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onboardingFragment = requireParentFragment() as OnboardingFragment

        // change view accordingly

        when (fragPosition) {
            0 -> {
                // change screen accordingly like color/ images
                binding.btnNext.isSelected = false
                binding.mainLayout.background =
                    resources.getDrawable(R.drawable.blue_gradient, null)
                binding.ivFirst.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_filled_white)
                binding.ivSecond.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_white_stroke)
                binding.ivThird.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_white_stroke)
            }

            1 -> {
                // change screen accordingly like color/ images
                binding.btnNext.isSelected = true
                binding.mainLayout.background =
                    resources.getDrawable(R.drawable.white_gradient, null)
                binding.verticalBarEnd.background =
                    resources.getDrawable(R.drawable.blue_angle_gradient, null)
                binding.ivImage.background = resources.getDrawable(R.drawable.blue_gradient, null)

                binding.btnNext.setImageResource(R.drawable.ic_circle_arrow_white)

                val vectorDrawable =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.intro_button_back)
                vectorDrawable?.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue_light
                    ), PorterDuff.Mode.SRC_IN
                )
                binding.rlCurve.background = vectorDrawable

                /*(binding.rlCurve.background as GradientDrawable).setColor(
                    requireContext().getColor(
                        R.color.blue_dark
                    )
                )*/

                binding.tvHeading.setTextColor(Color.BLACK)
                binding.tvDescription.setTextColor(Color.BLACK)
                binding.ivFirst.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_black_stroke)
                binding.ivSecond.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_filled_black)
                binding.ivThird.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_black_stroke)
            }

            2 -> {
                // change screen accordingly like color/ images
                binding.btnNext.isSelected = false
                binding.mainLayout.background = resources.getDrawable(R.drawable.red_gradient, null)
                binding.ivFirst.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_white_stroke)
                binding.ivSecond.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_white_stroke)
                binding.ivThird.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_filled_white)
            }
        }

        // handle the click event accordingly
        binding.btnNext.setOnClickListener {
            when (fragPosition) {
                0 -> {
                    // navigate to second screen
                    onboardingFragment.binding.viewPagerWalkThrough.currentItem = fragPosition + 1
                }

                1 -> {
                    // navigate to third screen
                    onboardingFragment.binding.viewPagerWalkThrough.currentItem = fragPosition + 1
                }

                2 -> {
                    // this is last page
                    // start new screen next to this onBoarding screen
                    // start activity or beginTransaction according to your needs
//                    requireActivity().supportFragmentManager.beginTransaction()
//                        .replace(R.id.franeLayout, SignInFragment()).addToBackStack("").commit()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            /*binding.mainLayout -> {

            }*/
        }
    }

    companion object {
        fun get(position: Int): SubOnboardingFragment {
            return SubOnboardingFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                }
            }
        }
    }
}