package com.codefun.saveaminutelauncher.presentation.fragments.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import com.codefun.saveaminutelauncher.domain.use_cases.apps_info.GetAppInfoListUseCase
import com.codefun.saveaminutelauncher.domain.use_cases.app.InsertAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Agam on 24,June,2022
 */

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val getAppInfoListUseCase: GetAppInfoListUseCase,
    private val insertAppsUseCase: InsertAppsUseCase
) : ViewModel() {

    fun saveAppsIntoDbForTheFirstTime() {
        viewModelScope.launch {
            insertAppsUseCase(getAppInfoListUseCase())
        }
    }

    fun getAppsFromDb() = appRepository.getApps().asLiveData()
}