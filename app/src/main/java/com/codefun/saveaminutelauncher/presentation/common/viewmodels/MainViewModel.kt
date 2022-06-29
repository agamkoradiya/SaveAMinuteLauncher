package com.codefun.saveaminutelauncher.presentation.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetDayInProgressUseCase
import com.codefun.saveaminutelauncher.domain.use_cases.progress_bar.GetYearInProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Agam on 27,June,2022
 */
private const val TAG = "HomeScreenViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDayInProgressUseCase: GetDayInProgressUseCase,
    private val getYearInProgressUseCase: GetYearInProgressUseCase,

    private val appRepository: AppRepository
) : ViewModel() {

    suspend fun getDayInProgress() = getDayInProgressUseCase()

    suspend fun getYearInProgress() = getYearInProgressUseCase()

    fun getHomeScreenApps() = appRepository.getHomeScreenApps().asLiveData()
}