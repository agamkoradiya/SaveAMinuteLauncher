package com.codefun.saveaminutelauncher.domain.repository

import androidx.lifecycle.LiveData
import com.codefun.saveaminutelauncher.domain.model.App
import kotlinx.coroutines.flow.Flow

/**
 * Created by Agam on 23,June,2022
 */

interface AppRepository {

    /**
     * Insert
     */
    suspend fun insertApp(app: App)

    suspend fun insertApps(apps: List<App>)

    /**
     * Update
     */

    suspend fun updateScreenTime(packageName: String, screenTime: String?)

    /**
     * Read
     */
    fun getApps(): Flow<List<App>>

    fun getHomeScreenApps(): Flow<List<App>>

    fun searchApps(searchQuery: String): LiveData<List<App>>

    suspend fun getAppsPackageName(): List<String>
}
