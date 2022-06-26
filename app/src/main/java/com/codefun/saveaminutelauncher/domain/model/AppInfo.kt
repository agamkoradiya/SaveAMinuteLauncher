package com.codefun.saveaminutelauncher.domain.model

import android.graphics.drawable.Drawable

/**
 * Created by Agam on 23,June,2022
 */

data class AppInfo(
    val packageName: String,
    val name: String,
    val icon: Drawable
)