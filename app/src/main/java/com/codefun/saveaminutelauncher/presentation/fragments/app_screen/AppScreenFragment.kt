package com.codefun.saveaminutelauncher.presentation.fragments.app_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.codefun.saveaminutelauncher.databinding.FragmentAppScreenBinding
import com.codefun.saveaminutelauncher.presentation.common.adapters.AppAdapter
import com.codefun.saveaminutelauncher.presentation.common.viewmodels.MainViewModel
import com.codefun.saveaminutelauncher.util.SEARCH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AppScreenFragment"

@AndroidEntryPoint
class AppScreenFragment : Fragment() {

    private var _binding: FragmentAppScreenBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()
    private var searchJob: Job? = null

    @Inject
    lateinit var appAdapter: AppAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSearchView()
        setUpRecyclerView()
        observeData()
    }

    private fun setUpSearchView() {
        binding.searchEdt.setOnFocusChangeListener { view, hasFocus ->
            // TODO: Handle focus change for outer click
        }

        binding.searchEdt.doOnTextChanged { text, start, before, count ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                mainViewModel.setSearchQuery(text.toString().trim())
                delay(SEARCH_DELAY)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.appsRv.adapter = appAdapter
    }

    private fun observeData() {
        mainViewModel.searchApps().observe(viewLifecycleOwner) {
            appAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}