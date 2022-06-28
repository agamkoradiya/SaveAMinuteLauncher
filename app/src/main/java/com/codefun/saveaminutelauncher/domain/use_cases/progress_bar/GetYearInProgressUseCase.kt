package com.codefun.saveaminutelauncher.domain.use_cases.progress_bar

import com.codefun.saveaminutelauncher.di.ProgressBarModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Agam on 27,June,2022
 */
private const val TAG = "GetYearInProgressUseCase"

class GetYearInProgressUseCase @Inject constructor(
    @Named(ProgressBarModule.DAYS_OF_THE_YEAR) private val totalDaysOfTheYear: Int,
    @Named(ProgressBarModule.YEAR_CALENDER) private val calendar: Calendar
) {
    // TODO: Check that is it working on last day of year and also make it more smooth
    suspend operator fun invoke(): Int = withContext(Dispatchers.IO) {
        val spentDays = calendar.get(Calendar.DAY_OF_YEAR)
        return@withContext (spentDays * 100) / totalDaysOfTheYear
    }

}