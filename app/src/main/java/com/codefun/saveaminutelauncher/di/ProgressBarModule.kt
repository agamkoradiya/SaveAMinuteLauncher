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
    fun provideTotalMsOfTheDay(): Long = 1.days.inWholeMilliseconds

    @Provides
    @Singleton
    @Named("day")
    fun provideCalenderToCalculateDayInProgress(): Calendar = Calendar.getInstance().apply {
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    @Provides
    @Singleton
    fun provideTotalDaysOfTheYear(): Int = Year.now().length()
    // Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)

    @Provides
    @Singleton
    @Named("year")
    fun provideCalenderToCalculateYearInProgress(): Calendar = Calendar.getInstance()
}