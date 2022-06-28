package com.codefun.saveaminutelauncher.domain.use_cases.apps_info

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Agam on 23,June,2022
 */

class GetUriFromDrawableUseCase @Inject constructor(
    private val application: Application
) {

    suspend operator fun invoke(icon: Drawable, fileName: String): String? = withContext(Dispatchers.IO) {
        val bitmap = icon.toBitmapOrNull()
        return@withContext bitmap?.let {
            application.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, it)
            }
            try {
                File(application.filesDir.absolutePath ,"/$fileName").toUri().toString()
            } catch (e: Exception) {
                null
            }
        }
    }
}