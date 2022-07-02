package com.codefun.saveaminutelauncher.presentation.fragments.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codefun.saveaminutelauncher.R
import com.codefun.saveaminutelauncher.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SplashScreenFragment"

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private val splashScreenViewModel by viewModels<SplashScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().navigate(R.id.action_splashScreenFragment_to_homeScreenFragment)

//        binding.txt.setOnClickListener {
//            findNavController().navigate(R.id.action_splashScreenFragment_to_homeScreenFragment)
//        }
//        // TODO: Fix This every time loading as you did in that launcher
//        splashScreenViewModel.saveAppsIntoDbForTheFirstTime()
//        splashScreenViewModel.getAppsFromDb().observe(viewLifecycleOwner) {
//            it?.let {
//                Log.i(TAG, "Data found ${it.size}")
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}