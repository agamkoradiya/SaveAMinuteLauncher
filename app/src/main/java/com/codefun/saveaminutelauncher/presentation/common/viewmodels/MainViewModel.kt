package com.codefun.saveaminutelauncher.presentation.common.viewmodels

import androidx.lifecycle.*
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

    /**
     * HomeScreenFragment
     */

    suspend fun getDayInProgress() = getDayInProgressUseCase()

    suspend fun getYearInProgress() = getYearInProgressUseCase()

    fun getHomeScreenApps() = appRepository.getHomeScreenApps().asLiveData()

    /**
     * AppScreenFragment
     */

    private val _searchQuery = MutableLiveData("")

    fun searchApps() = _searchQuery.switchMap {
        appRepository.searchApps(it)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}