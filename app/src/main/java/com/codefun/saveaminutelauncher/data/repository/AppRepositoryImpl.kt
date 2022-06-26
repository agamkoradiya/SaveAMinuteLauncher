package com.codefun.saveaminutelauncher.data.repository

import com.codefun.saveaminutelauncher.data.local.AppDao
import com.codefun.saveaminutelauncher.domain.model.App
import com.codefun.saveaminutelauncher.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Agam on 23,June,2022
 */

class AppRepositoryImpl @Inject constructor(private val appDao: AppDao) : AppRepository {

    override suspend fun insertApp(app: App) {
        appDao.insertApp(app)
    }

    override suspend fun insertApps(apps: List<App>) {
        appDao.insertApps(apps)
    }

    override fun getApps(): Flow<List<App>> {
        return appDao.getApps()
    }
}