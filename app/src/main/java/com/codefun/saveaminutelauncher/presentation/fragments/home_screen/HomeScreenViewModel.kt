package com.codefun.saveaminutelauncher.presentation.fragments.home_screen

import androidx.lifecycle.ViewModel
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetDayInProgressUseCase
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetYearInProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Agam on 27,June,2022
 */

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getDayInProgressUseCase: GetDayInProgressUseCase,
    private val getYearInProgressUseCase: GetYearInProgressUseCase
) : ViewModel() {

    suspend fun getDayInProgress() = getDayInProgressUseCase()

    suspend fun getYearInProgress() = getYearInProgressUseCase()
}