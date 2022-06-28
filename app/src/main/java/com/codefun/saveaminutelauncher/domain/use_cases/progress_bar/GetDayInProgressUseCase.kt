package com.codefun.saveaminutelauncher.domain.use_cases.progress_bar

import com.codefun.saveaminutelauncher.di.ProgressBarModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


/**
 * Created by Agam on 26,June,2022
 */

private const val TAG = "GetDayInProgressUseCase"

class GetDayInProgressUseCase @Inject constructor(
    @Named(ProgressBarModule.MS_OF_THE_DAY) private val totalMsOfTheDay: Long,
    @Named(ProgressBarModule.UTC_OFF_SET) private val utcOffSet: Long,
) {

    suspend operator fun invoke(): Int = withContext(Dispatchers.IO) {

        val spentTimeInMl = ((System.currentTimeMillis() + utcOffSet) % totalMsOfTheDay)
        val inPercentage = (spentTimeInMl * 100) / totalMsOfTheDay
        return@withContext inPercentage.toInt()

    }

}