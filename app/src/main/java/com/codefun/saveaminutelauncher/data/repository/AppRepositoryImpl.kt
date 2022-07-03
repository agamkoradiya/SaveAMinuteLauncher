package com.codefun.saveaminutelauncher.data.repository

import androidx.lifecycle.LiveData
import com.codefun.saveaminutelauncher.data.local.AppDao
import com.codefun.saveaminutelauncher.domain.model.App
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Agam on 23,June,2022
 */

class AppRepositoryImpl @Inject constructor(private val appDao: AppDao) : AppRepository {

    /**
     * Insert
     */

    override suspend fun insertApp(app: App) {
        appDao.insertApp(app)
    }

    override suspend fun insertApps(apps: List<App>) {
        appDao.insertApps(apps)
    }

    /**
     * Update
     */

    override suspend fun updateScreenTime(packageName: String, screenTime: String?) {
        appDao.updateScreenTime(packageName, screenTime)
    }

    /**
     * Read
     */

    override fun getApps(): Flow<List<App>> {
        return appDao.getApps()
    }

    override fun getHomeScreenApps(): Flow<List<App>> {
        return appDao.getHomeScreenApps()
    }

    override fun searchApps(searchQuery: String): LiveData<List<App>> {
        return appDao.searchApps(searchQuery)
    }

    override suspend fun getAppsPackageName(): List<String> {
        return appDao.getAppsPackageName()
    }
}