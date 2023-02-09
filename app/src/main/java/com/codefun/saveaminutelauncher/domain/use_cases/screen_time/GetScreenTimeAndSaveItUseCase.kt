package com.codefun.saveaminutelauncher.domain.use_cases.screen_time

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.util.Log
import com.codefun.saveaminutelauncher.di.ProgressBarModule
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Created by Agam on 04,July,2022
 */
private const val TAG = "GetScreenTimeAndSaveIt"

class GetScreenTimeAndSaveItUseCase @Inject constructor(
    @Named(ProgressBarModule.MS_OF_THE_DAY) private val totalMsOfTheDay: Long,
    @Named(ProgressBarModule.UTC_OFF_SET) private val utcOffSet: Long,
    private val appRepository: AppRepository,
    private val usageStatsManager: UsageStatsManager,
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - ((endTime + utcOffSet) % totalMsOfTheDay)

        val c: Calendar = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        Log.i(TAG, "invoke Calender time ---: ${c.timeInMillis}")
        Log.i(TAG, "invoke Begin time ---: ${beginTime}")
        Log.i(TAG, "invoke End time ---: ${endTime}")
        val usageStatsMap = usageStatsManager.queryAndAggregateUsageStats(beginTime, endTime)

        if (usageStatsMap.isNotEmpty()) {
            appRepository.getAppsPackageName().forEach { packageName ->
                usageStatsMap[packageName]?.let {
                    Log.i(
                        TAG,
                        "invoke: $packageName - ${it.totalTimeInForeground} -  ${
                            it.totalTimeInForeground.toDuration(DurationUnit.MILLISECONDS)
                                .getReadableScreenTime()
                        }"
                    )
                    appRepository.updateScreenTime(
                        packageName = packageName,
                        screenTime = it.totalTimeInForeground.toDuration(DurationUnit.MILLISECONDS)
                            .getReadableScreenTime()
                    )
                }
            }
        }

        val queryUsageStats = usageStatsManager
            .queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, beginTime,
                System.currentTimeMillis()
            )

        showAppUsageStats(queryUsageStats)
    }
}
private fun showAppUsageStats(usageStats: MutableList<UsageStats>) {
    usageStats.sortWith(Comparator { right, left ->
        compareValues(left.totalTimeInForeground, right.totalTimeInForeground)
    })

    usageStats.forEach { it ->
        Log.d(TAG, "packageName: ${it.packageName}, lastTimeUsed: ${Date(it.lastTimeUsed)}, " +
                "totalTimeInForeground: ${it.totalTimeInForeground}")
    }
}
// TODO: Return more specific value like space, two floating point etc.
fun Duration.getReadableScreenTime(): String? {
    return this.toComponents { hours, minutes, seconds, nanoseconds ->
        if (hours == 0L && minutes == 0 && seconds == 0) {
            null
        } else if (hours > 0L) {
            if (minutes > 0) {
                "${hours}h ${minutes}m"
            } else {
                "${hours}h"
            }
        } else if (minutes > 0) {
            if (seconds > 0) {
                "${minutes}m ${seconds}s"
            } else {
                "${minutes}m"
            }
        } else if (seconds > 0) {
            "${seconds}s"
        } else {
            null
        }
    }
}