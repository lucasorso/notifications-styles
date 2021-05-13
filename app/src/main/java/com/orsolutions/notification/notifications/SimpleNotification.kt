package com.orsolutions.notification.notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import javax.inject.Inject

class SimpleNotification @Inject constructor(private val context: Context) : NotificationDefault(context) {

    override fun build(
        title: String,
        message: String
    ): Notification = NotificationCompat.Builder(context, CURRENT_CHANNEL_ID)
        .setSmallIcon(CURRENT_ICON)
        .setContentTitle("$title - Simple ")
        .setContentText("$message - Simple ")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(defaultContentIntent)
        .build()
}