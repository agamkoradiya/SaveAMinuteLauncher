package com.codefun.saveaminutelauncher.presentation.fragments.home_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codefun.saveaminutelauncher.R
import com.codefun.saveaminutelauncher.databinding.FragmentHomeScreenBinding
import com.codefun.saveaminutelauncher.presentation.fragments.adapters.AppAdapter
import com.codefun.saveaminutelauncher.util.listeners.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

private const val TAG = "HomeScreenFragment"

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val homeScreenViewModel by viewModels<HomeScreenViewModel>()

    @Inject
    lateinit var appAdapter: AppAdapter

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

        inItSwipeTouchListener()
        setUpProgressBarSection()
        setUpRecyclerView()
    }

    private fun inItSwipeTouchListener() {
        binding.homeScreenRoot.setOnTouchListener(getOnTouchListener(requireContext()))
    }

    private fun getOnTouchListener(context: Context): View.OnTouchListener {
        return object : OnSwipeTouchListener(context) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Log.i(TAG, "onSwipeLeft: called")
                if (findNavController().currentDestination?.id == R.id.homeScreenFragment){
                    findNavController().navigate(R.id.action_homeScreenFragment_to_appScreenFragment)
                }
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                Log.i(TAG, "onSwipeRight: called")
            }

            override fun onSwipeUp() {
                super.onSwipeUp()
                Log.i(TAG, "onSwipeUp: called")
            }

            override fun onSwipeDown() {
                super.onSwipeDown()
                Log.i(TAG, "onSwipeDown: called")
            }

            override fun onLongClick() {
                super.onLongClick()
                Log.i(TAG, "onLongClick: called")
            }

            override fun onDoubleClick() {
                super.onDoubleClick()
                Log.i(TAG, "onDoubleClick: called")
            }

            override fun onTripleClick() {
                super.onTripleClick()
                Log.i(TAG, "onTripleClick: called")
            }
        }

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
                delay(1.minutes.inWholeMilliseconds)
//                delay(5.seconds.inWholeMilliseconds)
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

    private fun setUpRecyclerView() {
        binding.homeAppsRv.adapter = appAdapter

        homeScreenViewModel.getHomeScreenApps().observe(viewLifecycleOwner) {
            appAdapter.submitList(it)
        }

        appAdapter.setOnAppClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_appScreenFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}