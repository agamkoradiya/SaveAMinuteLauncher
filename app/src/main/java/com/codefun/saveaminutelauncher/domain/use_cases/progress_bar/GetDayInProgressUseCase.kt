package com.codefun.saveaminutelauncher.domain.use_cases.progress_bar

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Agam on 26,June,2022
 */

private const val TAG = "GetDayInProgressUseCase"

class GetDayInProgressUseCase @Inject constructor(
    private val totalMsOfTheDay: Long,
    @Named("day") private val calendar: Calendar
) {

    suspend operator fun invoke(): Int = withContext(Dispatchers.IO) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val currentMs = System.currentTimeMillis()
        val spentMs = currentMs - calendar.timeInMillis

        val inPercentage = (spentMs * 100) / totalMsOfTheDay
        return@withContext inPercentage.toInt()
    }

}