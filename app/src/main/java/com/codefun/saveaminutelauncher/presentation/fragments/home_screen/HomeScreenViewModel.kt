package com.codefun.saveaminutelauncher.presentation.fragments.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codefun.saveaminutelauncher.domain.use_cases.app.GetHomeScreenAppsUseCase
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetDayInProgressUseCase
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetYearInProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Agam on 27,June,2022
 */
private const val TAG = "HomeScreenViewModel"
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getDayInProgressUseCase: GetDayInProgressUseCase,
    private val getYearInProgressUseCase: GetYearInProgressUseCase,

    private val getHomeScreenAppsUseCase: GetHomeScreenAppsUseCase
) : ViewModel() {

    suspend fun getDayInProgress() = getDayInProgressUseCase()

    suspend fun getYearInProgress() = getYearInProgressUseCase()

    fun getHomeScreenApps() = getHomeScreenAppsUseCase().asLiveData()
}