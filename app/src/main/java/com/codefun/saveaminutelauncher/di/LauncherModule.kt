package com.codefun.saveaminutelauncher.di

import android.app.Application
import androidx.room.Room
import com.codefun.saveaminutelauncher.data.local.AppDatabase
import com.codefun.saveaminutelauncher.data.repository.AppRepositoryImpl
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import com.codefun.saveaminutelauncher.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton
import kotlin.time.Duration.Companion.days

/**
 * Created by Agam on 21,June,2022
 */

@Module
@InstallIn(SingletonComponent::class)
object LauncherModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppRepository(db: AppDatabase): AppRepository {
        return AppRepositoryImpl(db.getAppDao())
    }

}