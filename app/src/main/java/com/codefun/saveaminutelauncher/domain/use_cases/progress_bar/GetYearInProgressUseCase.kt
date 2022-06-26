package com.codefun.saveaminutelauncher.domain.use_cases.progress_bar

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
    private val totalDaysOfTheYear: Int,
    @Named("year") private val calendar: Calendar
) {

    suspend operator fun invoke(): Int = withContext(Dispatchers.IO) {
        val spentDays = calendar.get(Calendar.DAY_OF_YEAR)
        return@withContext (spentDays * 100) / totalDaysOfTheYear
    }

}