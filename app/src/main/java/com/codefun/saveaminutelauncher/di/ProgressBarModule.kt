package com.codefun.saveaminutelauncher.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Year
import java.util.*
import javax.inject.Named
import javax.inject.Singleton
import kotlin.time.Duration.Companion.days

/**
 * Created by Agam on 27,June,2022
 */

@Module
@InstallIn(SingletonComponent::class)
object ProgressBarModule {

    @Provides
    @Singleton
    @Named(MS_OF_THE_DAY)
    fun provideTotalMsOfTheDay(): Long = 1.days.inWholeMilliseconds

    @Provides
    @Singleton
    @Named(UTC_OFF_SET)
    fun provideUtcOffset(): Long = TimeZone.getDefault().getOffset(System.currentTimeMillis()).toLong()

    @Provides
    @Singleton
    @Named(DAY_CALENDER)
    fun provideCalenderToCalculateDayInProgress(): Calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    @Provides
    @Singleton
    @Named(DAYS_OF_THE_YEAR)
    fun provideTotalDaysOfTheYear(): Int = Year.now().length()
    // Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)

    @Provides
    @Singleton
    @Named(YEAR_CALENDER)
    fun provideCalenderToCalculateYearInProgress(): Calendar = Calendar.getInstance()

    const val YEAR_CALENDER = "YEAR_CALENDER"
    const val DAY_CALENDER = "DAY_CALENDER"
    const val UTC_OFF_SET = "UTC_OFF_SET"
    const val DAYS_OF_THE_YEAR ="DAYS_OF_THE_YEAR"
        const val MS_OF_THE_DAY = "MS_OF_THE_DAY"
}