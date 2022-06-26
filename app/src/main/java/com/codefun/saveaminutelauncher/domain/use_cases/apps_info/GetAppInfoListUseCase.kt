package com.codefun.saveaminutelauncher.domain.use_cases.apps_info

import android.app.Application
import android.content.Intent
import com.codefun.saveaminutelauncher.domain.model.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Agam on 23,June,2022
 */

class GetAppInfoListUseCase @Inject constructor(
    private val application: Application
) {

    suspend operator fun invoke(): List<AppInfo> = withContext(Dispatchers.IO) {
        val tempAppInfoList = mutableListOf<AppInfo>()

        val resolveInfoList = application
            .packageManager
            .queryIntentActivities(
                Intent(Intent.ACTION_MAIN, null)
                    .addCategory(Intent.CATEGORY_LAUNCHER), 0
            )

        for (resolveInfo in resolveInfoList) {
            if (resolveInfo.activityInfo.packageName != application.packageName) {
                val appInfo = AppInfo(
                    packageName = resolveInfo.activityInfo.packageName,
                    name = resolveInfo.loadLabel(application.packageManager).toString(),
                    icon = resolveInfo.activityInfo.loadIcon(application.packageManager)
                )
                tempAppInfoList.add(appInfo)
            }
        }

        return@withContext tempAppInfoList.toList()
    }

}