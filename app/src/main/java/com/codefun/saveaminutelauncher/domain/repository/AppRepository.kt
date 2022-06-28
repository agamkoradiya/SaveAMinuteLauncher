package com.codefun.saveaminutelauncher.domain.repository

import com.codefun.saveaminutelauncher.domain.model.App
import kotlinx.coroutines.flow.Flow

/**
 * Created by Agam on 23,June,2022
 */

interface AppRepository {

    suspend fun insertApp(app: App)

    suspend fun insertApps(apps: List<App>)

    fun getApps(): Flow<List<App>>

    fun getHomeScreenApps(): Flow<List<App>>
}
