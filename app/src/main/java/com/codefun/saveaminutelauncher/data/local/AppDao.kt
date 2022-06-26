package com.codefun.saveaminutelauncher.data.local

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(app: App)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApps(apps: List<App>)

    @Query("SELECT * FROM app ORDER BY name ASC")
    fun getApps(): Flow<List<App>>
}
