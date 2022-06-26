package com.codefun.saveaminutelauncher.domain.use_cases.app

import com.codefun.saveaminutelauncher.domain.model.App
import com.codefun.saveaminutelauncher.domain.model.AppInfo
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import com.codefun.saveaminutelauncher.domain.use_cases.apps_info.GetUriFromDrawableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Agam on 23,June,2022
 */

class InsertAppsUseCase @Inject constructor(
    private val appRepository: AppRepository,
    private val getUriFromDrawableUseCase: GetUriFromDrawableUseCase
) {
    suspend operator fun invoke(appInfoList: List<AppInfo>) {

        withContext(Dispatchers.IO) {

            appRepository.insertApps(
                appInfoList.map { appInfo ->
                    App(
                        packageName = appInfo.packageName,
                        name = appInfo.name,
                        iconUri = getUriFromDrawableUseCase(icon = appInfo.icon, fileName = appInfo.name),
                        screenTime = null,
                        isSecured = false,
                        isInHomeScreen = false,
                        isHidden = false
                    )
                }
            )

            /**
             * Below approach will send one by one event to our images flow
             * so we can't decide when all App inserted or not
             * so i used above approach which inserts whole list at a same time so we get final callback in images flow
             */
/*
            appInfoList.forEach { appInfo ->
                val app = App(
                    packageName = appInfo.packageName,
                    name = appInfo.name,
                    iconUri = getUriFromDrawableUseCase(icon = appInfo.icon, fileName = appInfo.name),
                    screenTime = "03 min",
                    isSecured = false,
                    isInHomeScreen = false,
                    isHidden = false
                )

                appRepository.insertApp(app)
            }
*/

        }
    }
}