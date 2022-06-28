package com.codefun.saveaminutelauncher.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Agam on 21,June,2022
 */

@Entity
data class App(
    @PrimaryKey(autoGenerate = false)
    val packageName: String,
    val name: String,
    val iconUri: String?,
    val screenTime: String?,
    val isSecured: Boolean,
    val isInHomeScreen: Boolean,
    val isHidden: Boolean
)
