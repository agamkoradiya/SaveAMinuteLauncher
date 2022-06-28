package com.codefun.saveaminutelauncher.domain.use_cases.app

import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import javax.inject.Inject

/**
 * Created by Agam on 27,June,2022
 */

class GetHomeScreenAppsUseCase @Inject constructor(private val appRepository: AppRepository) {

    operator fun invoke() = appRepository.getHomeScreenApps()
}