package com.codefun.saveaminutelauncher.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codefun.saveaminutelauncher.domain.model.App
import kotlinx.coroutines.flow.Flow

/**
 * Created by Agam on 23,June,2022
 */

@Dao
interface AppDao {

    /**
     * Insert
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: App)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApps(apps: List<App>)

    /**
     * Update
     */

    @Query("UPDATE App SET screenTime = :screenTime WHERE packageName = :packageName ")
    suspend fun updateScreenTime(packageName: String, screenTime: String?)

    /**
     * Read
     */

    @Query("SELECT * FROM app ORDER BY name ASC")
    fun getApps(): Flow<List<App>>

    @Query("SELECT * FROM app WHERE isInHomeScreen = 1 ORDER BY name ASC")
    fun getHomeScreenApps(): Flow<List<App>>

    @Query("SELECT * FROM app WHERE name like :searchQuery || '%'")
    fun searchApps(searchQuery: String): LiveData<List<App>>

    @Query("SELECT packageName FROM app")
    fun getAppsPackageName(): List<String>
}
