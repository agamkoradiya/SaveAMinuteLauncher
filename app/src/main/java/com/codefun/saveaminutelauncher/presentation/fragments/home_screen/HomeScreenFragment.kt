package com.codefun.saveaminutelauncher.presentation.fragments.home_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codefun.saveaminutelauncher.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

private const val TAG = "HomeScreenFragment"

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val homeScreenViewModel by viewModels<HomeScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpProgressBarSection()
    }

    private fun setUpProgressBarSection() {
        lifecycleScope.launchWhenResumed {
            var dayInPercentage: Int

            // TODO: Try to find proper way to handle below scenario
            while (isActive) {
                Log.i(TAG, "setUpProgressBarSection: DayInPercentage called")
                dayInPercentage = homeScreenViewModel.getDayInProgress()
                binding.apply {
                    dayInProgressValueTxt.text = "${String.format("%02d", dayInPercentage)} %"
                    dayInProgressIndicator.setProgress(dayInPercentage)
                }
                delay(5.seconds.inWholeMilliseconds)
            }

        }

        lifecycleScope.launch {
            val yearInPercentage = homeScreenViewModel.getYearInProgress()
            binding.apply {
                yearInProgressValueTxt.text = "${String.format("%02d", yearInPercentage)} %"
                yearInProgressIndicator.setProgress(yearInPercentage)
            }
            // TODO: Handle How to call this method for everyday?
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}