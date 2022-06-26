package com.codefun.saveaminutelauncher.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codefun.saveaminutelauncher.domain.model.App

/**
 * Created by Agam on 23,June,2022
 */

@Database(
    entities = [App::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}
