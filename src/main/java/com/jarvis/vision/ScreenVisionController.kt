package com.jarvis.vision

import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager

class ScreenVisionController(private val context: Context) {
    fun createConsentIntent(): Intent = context.getSystemService(MediaProjectionManager::class.java).createScreenCaptureIntent()
}
